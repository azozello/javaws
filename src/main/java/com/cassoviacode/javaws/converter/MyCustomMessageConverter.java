package com.cassoviacode.javaws.converter;

import com.fasterxml.jackson.databind.*;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConversionException;

import java.io.IOException;
import java.lang.reflect.Type;

public class MyCustomMessageConverter extends MappingJackson2MessageConverter {

    private Class payloadType;

    public void setPayloadType(Class payloadType) {
        this.payloadType = payloadType;
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        JavaType javaType = this.getJavaType(targetClass, conversionHint);
        Object payload = message.getPayload();
        Class view = this.getSerializationView(conversionHint);
        try {
            if (payload instanceof byte[]) {
                return view != null ?
                        this.getObjectMapper().readerWithView(view).forType(javaType).readValue((byte[])((byte[])payload)) :
                        this.getObjectMapper().readValue((byte[])((byte[])payload), javaType);
            } else {
                return view != null ?
                        this.getObjectMapper().readerWithView(view).forType(javaType).readValue(payload.toString()) :
                        this.getObjectMapper().readValue(payload.toString(), javaType);
            }
        } catch (IOException var8) {
            throw new MessageConversionException(message, "Could not read JSON: " + var8.getMessage(), var8);
        }
    }

    private JavaType getJavaType(Class<?> targetClass, @Nullable Object conversionHint) {
        if (this.payloadType != null) {
            return this.getObjectMapper().getTypeFactory().constructParametricType(targetClass, this.payloadType);
        }
        if (conversionHint instanceof MethodParameter) {
            MethodParameter param = (MethodParameter)conversionHint;
            param = param.nestedIfOptional();
            if (Message.class.isAssignableFrom(param.getParameterType())) {
                param = param.nested();
            }

            Type genericParameterType = param.getNestedGenericParameterType();
            Class<?> contextClass = param.getContainingClass();
            Type type = GenericTypeResolver.resolveType(genericParameterType, contextClass);
            return this.getObjectMapper().getTypeFactory().constructType(type);
        } else {
            return this.getObjectMapper().constructType(targetClass);
        }
    }
}

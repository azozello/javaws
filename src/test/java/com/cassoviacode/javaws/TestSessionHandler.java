package com.cassoviacode.javaws;

import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;


public class TestSessionHandler<T> extends StompSessionHandlerAdapter {
//TODO create methods for sending messages and handle topic subscriptions

    private final CompletableFuture<T> completableFuture = new CompletableFuture<>();
    private final Class payloadType;

    public TestSessionHandler() {
        this.payloadType = Void.class;
    }

    public TestSessionHandler(Class payloadType) {
        this.payloadType = payloadType;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return payloadType;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        completableFuture.complete((T) payload);
    }

    public CompletableFuture<T> getCompletableFuture() {
        return completableFuture;
    }
}
package com.cassoviacode.javaws;

import com.cassoviacode.javaws.converter.MyCustomMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;


@SpringBootApplication(scanBasePackages = {"com.cassoviacode"})
@EntityScan("com.cassoviacode.javaws.model")
public class JavaWsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JavaWsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaWsApplication.class, args);
	}

	@Bean
	public MessageConverter customMessageConverter() {
		return new MyCustomMessageConverter();
	}
}

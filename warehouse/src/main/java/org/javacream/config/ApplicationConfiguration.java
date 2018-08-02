package org.javacream.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages="org.javacream")
@EntityScan(basePackages="org.javacream")
public class ApplicationConfiguration {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.basicAuthorization("javacream", "javacream").build();

	}


}

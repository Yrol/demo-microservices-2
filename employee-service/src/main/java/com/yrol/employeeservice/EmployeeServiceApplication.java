package com.yrol.employeeservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Service REST API",
				description = "Employee Service REST API Documentation",
				version = "1.0",
				contact = @Contact(
						name = "Yrol Fernando",
						email = "",
						url = "https://www.yrol.blog"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)


@SpringBootApplication
@EnableFeignClients // Enable Feign Clients for API calls
public class EmployeeServiceApplication {
	/**
	 * Registering RestTemplate as Bean in Spring IOC to be used for calling other microservices endpoints
	 * **/
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	/**
	 * Registering WebClient as Bean in Spring IOC to be used for calling other microservices endpoints
	 * **/
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}

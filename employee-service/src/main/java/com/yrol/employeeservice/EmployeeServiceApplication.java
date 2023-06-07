package com.yrol.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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

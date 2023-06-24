package com.yrol.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * Service Registry and Discovery server - act as the registry for registering each microservice as clients.
 * This will allow to call the microservices from each other using the client name (if registered as a client) instead of the hostname.
 * In case of multiple instances of a microservice (load balancing) - this will call the available instance automatically.
 * Base on Spring Cloud Netflix Eureka
 * */

@SpringBootApplication
@EnableEurekaServer // Enabling this project as the Eureka server
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}

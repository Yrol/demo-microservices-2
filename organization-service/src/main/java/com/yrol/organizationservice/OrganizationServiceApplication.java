package com.yrol.organizationservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
		info = @Info(
				title = "Organization Service REST API",
				description = "Organization Service REST API Documentation",
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
public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}

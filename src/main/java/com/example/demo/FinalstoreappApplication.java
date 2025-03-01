package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(
			title = "Store Application REST API Documentation",
			description = "Store Application REST documentation",
			contact = @Contact(
						name = "Yashpal Gawali",
						email = "support@tidyhomz.com",
						url = "tidyhomz.com"
					),
			license = @License(
						name = "Apache 2.0",
						url = "www.tidyhomz.com"
					)
			),
			externalDocs = @ExternalDocumentation(
						description = "Store Application REST Documentation"
					)
		)

public class FinalstoreappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalstoreappApplication.class, args);
	}
}

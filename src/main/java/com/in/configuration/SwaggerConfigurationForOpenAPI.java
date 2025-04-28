package com.in.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfigurationForOpenAPI {
	
	@Bean
	public OpenAPI createOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Student Service")//title
						.description("This is Student Application"));//description
				
	}

}

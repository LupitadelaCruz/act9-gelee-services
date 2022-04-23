package com.gelee.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication (scanBasePackages = "com.gelee.api.controller")
@ComponentScan(basePackages="com.gelee.api")
@EnableJpaRepositories("com.gelee.api.repository")
@EntityScan("com.gelee.api.model")
@OpenAPIDefinition(info = @Info(title = "Gelee API - REST ::  U V M", version = "1.0", description = "API REST que expone servicios para venta de gelatinas. Proyecto Integrador Etapa 3. UVM ."))
public class GeleeServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeleeServicesApplication.class);
	}

}

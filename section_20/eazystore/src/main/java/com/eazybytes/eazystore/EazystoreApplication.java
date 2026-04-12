package com.eazybytes.eazystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// if import spring-boot-starter-data-jpa as a Maven dependency,
// The DataSourceAutoConfiguration.class can be excluded
@SpringBootApplication
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
//@ComponentScan(basePackages = {"com.eazybytes.eazystore.controller"})
//@EnableJpaRepositories // Optional if using entity and repository in the main folder
//@EntityScan // Optional if using entity and repository in the main folder with the Application
public class EazystoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazystoreApplication.class, args);
	}
}

package com.eazybytes.eazystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// if import spring-boot-starter-data-jpa as a Maven dependency,
// The DataSourceAutoConfiguration.class can be excluded
@SpringBootApplication
//@ComponentScan(basePackages = {"com.eazybytes.eazystore.controller"})
public class EazystoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazystoreApplication.class, args);
	}

}

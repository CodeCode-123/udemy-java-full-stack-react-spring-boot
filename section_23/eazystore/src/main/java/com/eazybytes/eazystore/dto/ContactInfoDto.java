package com.eazybytes.eazystore.dto;
import org.springframework.boot.context.properties.ConfigurationProperties;

//the annotation binds external configuration values from application.properties
@ConfigurationProperties("contact")
public record ContactInfoDto(String phone, String email, String address) {
}

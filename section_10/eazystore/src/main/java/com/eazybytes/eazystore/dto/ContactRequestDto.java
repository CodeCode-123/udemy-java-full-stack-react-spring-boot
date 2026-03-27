package com.eazybytes.eazystore.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

public class ContactRequestDto {
    private String name;
    private String email;
    private String mobileNumber;
    private String message;
}

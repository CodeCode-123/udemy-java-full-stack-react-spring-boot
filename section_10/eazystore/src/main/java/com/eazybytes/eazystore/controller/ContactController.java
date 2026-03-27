package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ContactRequestDto;
import com.eazybytes.eazystore.dto.ProductDto;
import com.eazybytes.eazystore.entity.Contact;
import com.eazybytes.eazystore.service.IContactService;
import com.eazybytes.eazystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor // without writing constructor with args
public class ContactController {
    private final IContactService contactService;

    @PostMapping
    public String saveContact(@RequestBody ContactRequestDto contactRequestDto) {
        boolean isSaved = contactService.saveContact(contactRequestDto);
        if (isSaved) {
            return "Request processed successfully";
        }
        return "An error occurred. Please try again or contact Dev team";
    }
}

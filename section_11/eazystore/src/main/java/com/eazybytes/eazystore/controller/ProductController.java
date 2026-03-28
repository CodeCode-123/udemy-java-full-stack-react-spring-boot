package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ErrorResponseDto;
import com.eazybytes.eazystore.dto.ProductDto;
import com.eazybytes.eazystore.entity.Product;
import com.eazybytes.eazystore.repository.ProductRepository;
import com.eazybytes.eazystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor // without writing constructor with args
//@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final IProductService productService;
//    @Autowired not necessary if use @RequiredArgsConstructor
//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() throws InterruptedException { // DTO Pattern
        return ResponseEntity.ok().body(productService.getProducts());
    }

    // higher priority than the global exception handler if writing the exception handler inside
    // the controller class with @ExceptionHandler annotation
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.SERVICE_UNAVAILABLE,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.SERVICE_UNAVAILABLE);
    }
}

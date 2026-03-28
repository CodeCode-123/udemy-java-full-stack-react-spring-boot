package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ProductDto;
import com.eazybytes.eazystore.entity.Product;
import com.eazybytes.eazystore.repository.ProductRepository;
import com.eazybytes.eazystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ProductDto> getProducts() throws InterruptedException { // DTO Pattern
        System.out.println("Testing code changes");
        return productService.getProducts();
    }
}

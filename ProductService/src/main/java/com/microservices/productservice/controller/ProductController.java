package com.microservices.productservice.controller;


import com.microservices.productservice.dto.ProductRequestDto;
import com.microservices.productservice.dto.ProductResponseDto;
import com.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public String create(@RequestBody ProductRequestDto productRequestDto) {
        return productService.save(productRequestDto)
                ? "Successfully Created!"
                : "An unexpected error occured!";
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll(@RequestParam(required = false) List<Long> productIds) {

        var productResponseDtoList = productService.findAll(productIds);

        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/byCategory")
    public ResponseEntity<List<ProductResponseDto>> findByCategory(@RequestParam String category) {

        var productResponseDtoList = productService.findByCategory(category);

        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/productExists/{productId}")
    public ResponseEntity<Boolean> checkIfProductExists(@PathVariable long productId) {

        boolean productIsValid = productService.checkIfProductIsValid(productId);

        if (productIsValid)
            return ResponseEntity.ok(true);

        return ResponseEntity.notFound().build();
    }

}
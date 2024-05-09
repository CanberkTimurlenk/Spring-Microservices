package com.robotdreams.productservice.controller;


import com.robotdreams.productservice.dto.ProductRequestDto;
import com.robotdreams.productservice.dto.ProductResponseDto;
import com.robotdreams.productservice.service.ProductService;
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

        var productResponseDtos = productService.findAll(productIds);

        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }


    @GetMapping("/byCategory")
    public ResponseEntity<List<ProductResponseDto>> findByCategory(@RequestParam String category) {

        var productResponseDtos = productService.findByCategory(category);

        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/productValidity")
    public ResponseEntity<Boolean> checkIfProductIsValid(long productId) {

        if (productService.checkIfProductIsValid(productId))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

}
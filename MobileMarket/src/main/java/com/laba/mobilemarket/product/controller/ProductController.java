package com.laba.mobilemarket.product.controller;

import com.laba.mobilemarket.product.dto.ProductRequestDto;
import com.laba.mobilemarket.product.dto.ProductResponseDto;
import com.laba.mobilemarket.product.feign.ProductFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductFeignClient productFeignClient;

    @PostMapping
    public String save(@RequestBody ProductRequestDto productRequestDto) {
        return productFeignClient.save(productRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll(@RequestParam(required = false) List<Long> productIds) {
        List<ProductResponseDto> productResponseDtos = productFeignClient.findAll(productIds);
        return ResponseEntity.ok(productResponseDtos);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<ProductResponseDto>> findByCategory(@RequestParam String category) {
        List<ProductResponseDto> productResponseDtos = productFeignClient.findByCategory(category);
        return ResponseEntity.ok(productResponseDtos);
    }

}

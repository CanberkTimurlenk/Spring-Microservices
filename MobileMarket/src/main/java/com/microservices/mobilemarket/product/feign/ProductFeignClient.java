package com.microservices.mobilemarket.product.feign;

import com.microservices.mobilemarket.product.dto.ProductRequestDto;
import com.microservices.mobilemarket.product.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ProductService")
public interface ProductFeignClient {

    @PostMapping
    String save(@RequestBody ProductRequestDto productRequestDto);

    @GetMapping
    List<ProductResponseDto> findAll(@RequestParam(required = false) List<Long> productIds);

    @GetMapping("/products/byCategory")
    List<ProductResponseDto> findByCategory(@RequestParam String category);
}
package com.laba.mobilemarket.feign;


import com.laba.mobilemarket.dto.product.ProductRequestDto;
import com.laba.mobilemarket.dto.product.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ProductService")
public interface ProductFeignClient {

    @PostMapping("/products")
    String createProduct(@RequestBody ProductRequestDto productRequestDto);

    @GetMapping("/products")
    List<ProductResponseDto> findAllProducts(@RequestParam(required = false) List<Long> productIds);

    @GetMapping("/products/byCategory")
    List<ProductResponseDto> findProductsByCategory(@RequestParam String category);
}




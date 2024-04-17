package com.laba.mobilemarket.product;

import com.laba.mobilemarket.dto.product.ProductRequestDto;
import com.laba.mobilemarket.dto.product.ProductResponseDto;
import com.laba.mobilemarket.feign.ProductFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductFeignClient productFeignClient;

    @PostMapping("/save")
    public String createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productFeignClient.createProduct(productRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts(@RequestParam(required = false) List<Long> productIds) {
        List<ProductResponseDto> productResponseDtos = productFeignClient.findAllProducts(productIds);
        return ResponseEntity.ok(productResponseDtos);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<ProductResponseDto>> findProductsByCategory(@RequestParam String category) {
        List<ProductResponseDto> productResponseDtos = productFeignClient.findProductsByCategory(category);
        return ResponseEntity.ok(productResponseDtos);
    }

}

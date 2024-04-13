package com.robotdreams.orderservice.feign;

import com.robotdreams.orderservice.dto.response.external.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "productService")
public interface ProductFeignClient {

    @GetMapping("/products")
    public List<ProductResponseDto> getProductsById(@RequestParam List<Long> productIds);
}

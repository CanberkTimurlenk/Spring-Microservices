package com.microservices.orderservice.feign;

import com.microservices.orderservice.dto.response.external.ProductResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "productService")
public interface ProductFeignClient {

    @GetMapping("/products")
    @CircuitBreaker(name = "GetProductCBreaker", fallbackMethod = "getProductServiceFallback")
    public List<ProductResponseDto> getProductsById(@RequestParam List<Long> productIds);

    default List<ProductResponseDto> getProductServiceFallback(@RequestParam List<Long> productIds, Exception e) {
        // Execute If ProductService Is Not Accessible

        throw new RuntimeException(
                ("An error occured while retrieving product info for product Ids: "
                        + String.join(",", productIds.stream().map(Object::toString).toList())));
    }
}

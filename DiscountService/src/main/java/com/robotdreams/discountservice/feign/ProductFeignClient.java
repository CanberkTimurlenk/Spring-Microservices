package com.robotdreams.discountservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ProductService")
public interface ProductFeignClient {

    @GetMapping("/products/productExists")
    public ResponseEntity<Boolean> checkIfProductExists(long productId);

}

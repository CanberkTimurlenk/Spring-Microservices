package com.robotdreams.discountservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProductService", dismiss404 = true)
public interface ProductFeignClient {

    @GetMapping("/products/productExists/{productId}")
    Boolean checkIfProductExists(@PathVariable long productId);

}

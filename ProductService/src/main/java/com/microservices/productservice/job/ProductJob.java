package com.microservices.productservice.job;

import com.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductJob {

    @Value(value = "${product.category}")
    private String category;

    private final ProductService productService;

//    @Scheduled(fixedDelay = 10000)
//    public void updateProduct() {
//        productService.productListByCategoryUpdate(category);
//    }
}
package com.robotdreams.productservice.job;

import com.robotdreams.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
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
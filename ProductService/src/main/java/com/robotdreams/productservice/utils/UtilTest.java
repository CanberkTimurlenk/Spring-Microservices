package com.robotdreams.productservice.utils;

import com.robotdreams.productservice.dto.ProductDetailRequestDto;
import com.robotdreams.productservice.dto.ProductRequestDto;
import com.robotdreams.productservice.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class UtilTest {

    private final ProductService productService;

    @PostConstruct
    public void saveSampleData() {

        ProductDetailRequestDto phoneDetail = new ProductDetailRequestDto("6GB RAM", "123456789");

        ProductRequestDto phone = ProductRequestDto.builder()
                .name("Smartphone")
                .category("Electronics")
                .photoUrl("example.com/phone.jpg")
                .description("This is a smartphone")
                .price(499.99)
                .productDetail(phoneDetail)
                .build();


        ProductDetailRequestDto computerDetail = new ProductDetailRequestDto("Intel Core i7", "987654321");

        ProductRequestDto computer = ProductRequestDto.builder()
                .name("Laptop")
                .category("Electronics")
                .photoUrl("example.com/laptop.jpg")
                .description("This is a laptop")
                .price(1299.99)
                .productDetail(computerDetail)
                .build();

        productService.saveAll(List.of(phone, computer));
    }

}

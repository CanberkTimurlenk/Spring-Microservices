package com.microservices.orderservice.util;

import com.microservices.orderservice.dto.request.OrderRequestDto;
import com.microservices.orderservice.dto.request.ProductRequestDto;
import com.microservices.orderservice.service.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class UtilTest {

//    private final OrderService orderService;
//
//
//    @PostConstruct
//    public void test()
//    {
//        ProductRequestDto product1 = new ProductRequestDto(1L, 2);
//        Set<ProductRequestDto> orderProducts = Set.of(product1);
//
//        OrderRequestDto orderRequest = new OrderRequestDto(orderProducts, "This is a sample order description", 12345L);
//
//        orderService.save(orderRequest);
//    }
}

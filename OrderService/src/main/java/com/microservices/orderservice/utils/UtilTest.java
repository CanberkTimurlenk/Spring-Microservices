package com.microservices.orderservice.utils;

import com.microservices.orderservice.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class UtilTest {

    private final OrderService orderService;
}

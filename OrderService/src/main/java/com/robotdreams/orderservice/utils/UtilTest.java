package com.robotdreams.orderservice.utils;

import com.robotdreams.orderservice.service.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class UtilTest {

    private final OrderService orderService;
}

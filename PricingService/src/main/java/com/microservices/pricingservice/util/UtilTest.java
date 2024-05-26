package com.microservices.pricingservice.util;

import com.microservices.pricingservice.service.PricingService;
import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UtilTest {

    private final PricingService pricingService;

    @PostConstruct
    public void Test() {

    }
}
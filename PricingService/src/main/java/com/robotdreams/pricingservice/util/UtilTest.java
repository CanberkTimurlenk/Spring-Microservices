package com.robotdreams.pricingservice.util;

import com.robotdreams.pricingservice.dto.pricecontainer.request.PriceContainerRequestDto;
import com.robotdreams.pricingservice.dto.pricecontainer.request.ContainerItemRequestDto;
import com.robotdreams.pricingservice.service.PricingService;
import jakarta.annotation.PostConstruct;
import lombok.Data;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class UtilTest {

    private final PricingService pricingService;

    @PostConstruct
    public void Test() {

    }
}
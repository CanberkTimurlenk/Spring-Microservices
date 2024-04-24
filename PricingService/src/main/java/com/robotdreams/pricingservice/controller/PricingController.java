package com.robotdreams.pricingservice.controller;

import com.robotdreams.pricingservice.dto.pricecontainer.request.ContainerItemRequestDto;
import com.robotdreams.pricingservice.dto.pricecontainer.request.PriceContainerRequestDto;
import com.robotdreams.pricingservice.service.PricingService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;


@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final PricingService pricingService;

    @GetMapping("/test")
    public void test() {
        ContainerItemRequestDto item1 = new ContainerItemRequestDto(
                1L, // productId
                2, // quantity
                BigDecimal.valueOf(10.0), // unitPrice
                "DISCOUNT5" // discountCode
        );

        ContainerItemRequestDto item2 = new ContainerItemRequestDto(
                2L, // productId
                1, // quantity
                BigDecimal.valueOf(15.0), // unitPrice
                "NO_DISCOUNT" // discountCode
        );

        ContainerItemRequestDto item3 = new ContainerItemRequestDto(
                3L, // productId
                3, // quantity
                BigDecimal.valueOf(8.0), // unitPrice
                "DISCOUNT10" // discountCode
        );

        ContainerItemRequestDto item4 = new ContainerItemRequestDto(
                4L, // productId
                2, // quantity
                BigDecimal.valueOf(30.0), // unitPrice
                "DISCOUNT20" // discountCode
        );

        ContainerItemRequestDto item5 = new ContainerItemRequestDto(
                5L, // productId
                4, // quantity
                BigDecimal.valueOf(5.0), // unitPrice
                "DISCOUNT15" // discountCode
        );

        // PriceContainerRequestDto
        PriceContainerRequestDto cartRequestDto = new PriceContainerRequestDto(
                1L, // userId
                Arrays.asList(item1, item2, item3, item4, item5), // cartItems
                "NEWUSER10" // discountCode
        );
        var result = pricingService.getPricedCart(cartRequestDto);

    }
}
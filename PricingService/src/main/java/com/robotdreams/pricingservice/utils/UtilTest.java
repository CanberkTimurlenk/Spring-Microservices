package com.robotdreams.pricingservice.utils;

import com.robotdreams.pricingservice.dto.cart.request.PricedCartItemRequestDto;
import com.robotdreams.pricingservice.service.PricingService;
import jakarta.annotation.PostConstruct;
import lombok.Data;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.robotdreams.pricingservice.dto.cart.request.PricedCartRequestDto;

import java.math.BigDecimal;


@Data
@Service
@RequiredArgsConstructor
public class UtilTest {

    private final PricingService pricingService;


    @PostConstruct
    public void Test() {

        PricedCartItemRequestDto item1 = new PricedCartItemRequestDto(
                1L, // productId
                2, // quantity
                BigDecimal.valueOf(10.0), // unitPrice
                "DISCOUNT5" // discountCode
        );

        PricedCartItemRequestDto item2 = new PricedCartItemRequestDto(
                2L, // productId
                1, // quantity
                BigDecimal.valueOf(15.0), // unitPrice
                "NO_DISCOUNT" // discountCode
        );

        PricedCartItemRequestDto item3 = new PricedCartItemRequestDto(
                3L, // productId
                3, // quantity
                BigDecimal.valueOf(8.0), // unitPrice
                "DISCOUNT10" // discountCode
        );

        PricedCartItemRequestDto item4 = new PricedCartItemRequestDto(
                4L, // productId
                2, // quantity
                BigDecimal.valueOf(30.0), // unitPrice
                "DISCOUNT20" // discountCode
        );

        PricedCartItemRequestDto item5 = new PricedCartItemRequestDto(
                5L, // productId
                4, // quantity
                BigDecimal.valueOf(5.0), // unitPrice
                "DISCOUNT15" // discountCode
        );

        // PricedCartRequestDto örneği oluşturma
        PricedCartRequestDto cartRequestDto = new PricedCartRequestDto(
                12345L, // userId
                Arrays.asList(item1, item2, item3, item4, item5), // cartItems
                "NEWUSER10" // discountCode
        );

        var result = pricingService.getPricedCart(cartRequestDto);

        int x =5 ;
    }

}

package com.robotdreams.pricingservice.dto.cart.request;


import java.math.BigDecimal;

public record PricedCartItemRequestDto(long productId,
                                       int quantity,
                                       BigDecimal unitPrice,
                                       String discountCode) {
}
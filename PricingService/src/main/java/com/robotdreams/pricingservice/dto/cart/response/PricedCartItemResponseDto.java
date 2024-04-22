package com.robotdreams.pricingservice.dto.cart.response;

import java.math.BigDecimal;

public record PricedCartItemResponseDto(long productId,
                                        int quantity,
                                        BigDecimal itemTotalPrice,
                                        BigDecimal unitPrice) {

}
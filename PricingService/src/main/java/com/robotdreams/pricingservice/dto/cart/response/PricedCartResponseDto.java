package com.robotdreams.pricingservice.dto.cart.response;

import java.math.BigDecimal;
import java.util.List;

public record PricedCartResponseDto(long userId,
                                    List<PricedCartItemResponseDto> cartItems, BigDecimal cartTotalPrice,
                                    boolean isPremiumCart) {

}

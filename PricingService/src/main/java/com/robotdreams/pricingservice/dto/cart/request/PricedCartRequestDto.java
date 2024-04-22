package com.robotdreams.pricingservice.dto.cart.request;

import java.util.List;


public record PricedCartRequestDto(long userId,
                                   List<PricedCartItemRequestDto> cartItems, String discountCode) {

}

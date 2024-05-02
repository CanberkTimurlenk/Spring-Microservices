package com.laba.mobilemarket.basket.dto;

import java.util.List;

public record ShoppingCartRequestDto(long userId, String discountCode,
                                     List<CartItemRequestDto> items) {

}




package com.robotdreams.basketservice.dto;

import com.robotdreams.basketservice.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public record ShoppingCartRequestDto(long userId, String discountCode,
                                     List<CartItemRequestDto> items) {

}




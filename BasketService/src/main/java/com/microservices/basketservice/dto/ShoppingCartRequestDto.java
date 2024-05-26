package com.microservices.basketservice.dto;

import java.util.List;

public record ShoppingCartRequestDto(long userId, String discountCode,
                                     List<CartItemRequestDto> items) {

}




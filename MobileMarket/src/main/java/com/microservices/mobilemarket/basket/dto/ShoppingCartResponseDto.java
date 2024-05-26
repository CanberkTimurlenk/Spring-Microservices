package com.microservices.mobilemarket.basket.dto;

import java.math.BigDecimal;
import java.util.List;

public record ShoppingCartResponseDto(long userId, String discountCode, BigDecimal discountAmount,
                                      BigDecimal cartTotalAmount,
                                      List<CartItemResponseDto> items) {

}
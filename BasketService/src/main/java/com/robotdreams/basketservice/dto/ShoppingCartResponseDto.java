package com.robotdreams.basketservice.dto;

import com.robotdreams.basketservice.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public record ShoppingCartResponseDto(long userId, String discountCode, BigDecimal discountAmount,
                                      BigDecimal cartTotalAmount,
                                      List<CartItem> items) {

}
                                      


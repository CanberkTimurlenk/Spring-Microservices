package com.laba.mobilemarket.basket.dto;

public record CartItemRequestDto(int quantity, long productId, String discountCode) {
}

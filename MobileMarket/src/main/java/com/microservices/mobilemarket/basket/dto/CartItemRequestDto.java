package com.microservices.mobilemarket.basket.dto;

public record CartItemRequestDto(int quantity, long productId, String discountCode) {
}

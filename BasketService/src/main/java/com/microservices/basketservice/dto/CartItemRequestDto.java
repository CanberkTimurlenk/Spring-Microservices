package com.microservices.basketservice.dto;

public record CartItemRequestDto(int quantity, long productId, String discountCode) {
}

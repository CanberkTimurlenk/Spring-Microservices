package com.robotdreams.basketservice.dto;

public record CartItemRequestDto(int quantity, long productId, String discountCode) {
}

package com.microservices.basketservice.dto;

import java.math.BigDecimal;

public record CartItemResponseDto(long id, int quantity, BigDecimal unitPrice, long productId, String discountCode) {

}

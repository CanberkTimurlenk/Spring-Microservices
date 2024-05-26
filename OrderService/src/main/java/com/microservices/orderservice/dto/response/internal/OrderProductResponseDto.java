package com.microservices.orderservice.dto.response.internal;

import java.math.BigDecimal;

public record OrderProductResponseDto(
        Long productId,
        int quantity,
        BigDecimal amount) {
}

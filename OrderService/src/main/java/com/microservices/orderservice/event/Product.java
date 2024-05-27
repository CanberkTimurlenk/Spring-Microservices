package com.microservices.orderservice.event;

import java.math.BigDecimal;

public record Product(
        long productId,
        int quantity,
        BigDecimal price
) {
}
package com.microservices.orderservice.event.ordercreated;

import java.math.BigDecimal;

public record Product(
        long productId,
        int quantity,
        BigDecimal price
) {
}
package com.microservices.orderservice.event;

import java.math.BigDecimal;
import java.util.Set;

public record OrderCreatedEvent(
        long orderId,
        String orderNumber,
        Set<Product> products,
        long userId,
        BigDecimal price) {
}


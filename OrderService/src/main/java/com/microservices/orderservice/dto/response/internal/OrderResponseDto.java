package com.microservices.orderservice.dto.response.internal;

import com.microservices.orderservice.event.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public record OrderResponseDto(Date createDate,
                               Date updateDate,
                               String serverTime,
                               long orderId,
                               long userId,
                               BigDecimal shippingCost,
                               BigDecimal totalAmount,
                               Set<Product> products,
                               String description
) {
}
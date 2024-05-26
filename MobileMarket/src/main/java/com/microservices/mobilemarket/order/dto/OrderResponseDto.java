package com.microservices.mobilemarket.order.dto;

import java.math.BigDecimal;
import java.util.Date;

public record OrderResponseDto(Date createDate,
                               Date updateDate,
                               String serverTime,
                               long orderId,
                               long userId,
                               BigDecimal shippingCost,
                               String description
) {
}
package com.robotdreams.orderservice.dto.response.internal;

import java.io.Serializable;
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
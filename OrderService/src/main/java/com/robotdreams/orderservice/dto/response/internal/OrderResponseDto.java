package com.robotdreams.orderservice.dto.response.internal;

import java.io.Serializable;
import java.util.Date;

public record OrderResponseDto(Date createDate,
                               Date updateDate,
                               String serverTime,
                               long orderId,
                               String description
) {
}
package com.microservices.discountservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ProductCouponResponseDto(String id, long productId, String description,
                                       BigDecimal amount, String couponCode,
                                       Date createDate, Date updateDate, Date expirationDate) {
}

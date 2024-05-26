package com.microservices.discountservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ProductCouponRequestDto(Long productId, String description,
                                      BigDecimal amount, String couponCode, Date expirationDate) {
}

package com.microservices.discountservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public record CartCouponRequestDto(String description,
                                   BigDecimal amount, String couponCode, Date expirationDate) {

}

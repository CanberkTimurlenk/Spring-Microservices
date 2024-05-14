package com.robotdreams.discountservice.dto;

import java.math.BigDecimal;

public record CouponRequestDto(Long productId, String description,
                               BigDecimal amount, String couponCode) {

}

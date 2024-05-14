package com.robotdreams.discountservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public record CouponResponseDto(String id, long productId, String description,
                                BigDecimal amount, String couponCode,
                                Date createDate, Date updateDate) {

}

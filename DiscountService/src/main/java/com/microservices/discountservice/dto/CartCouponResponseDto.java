package com.microservices.discountservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public record CartCouponResponseDto(String id, String description,
                                    BigDecimal amount, String couponCode,
                                    Date createDate, Date updateDate, Date expirationDate) {

}

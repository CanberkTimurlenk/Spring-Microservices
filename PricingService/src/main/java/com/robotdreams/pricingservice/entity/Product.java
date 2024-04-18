package com.robotdreams.pricingservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class Product {

    private long productId;
    private BigDecimal unitPrice;
    private long discountCode;
}

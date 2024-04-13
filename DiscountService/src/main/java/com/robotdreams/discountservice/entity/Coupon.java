package com.robotdreams.discountservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Coupons")
public class Coupon extends BaseEntity implements Serializable {

    private long productId;
    private String description;
    private BigDecimal Amount;
    private String couponCode;

}

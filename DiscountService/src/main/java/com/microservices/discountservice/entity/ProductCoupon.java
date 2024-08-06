package com.microservices.discountservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ProductCoupons")
public class ProductCoupon extends Coupon implements Serializable {
    private long productId;
}
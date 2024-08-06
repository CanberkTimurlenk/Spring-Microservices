package com.microservices.discountservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Entity
@Table(name = "CartCoupons")
@Getter
@Setter
public class CartCoupon extends Coupon implements Serializable {

}
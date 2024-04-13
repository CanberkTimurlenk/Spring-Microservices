package com.robotdreams.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Table(name = "ProductDetails")
@Entity
@Getter
@Setter
public class ProductDetail extends BaseEntity implements Serializable {

    private String productInfo;
    private String productSerialNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}

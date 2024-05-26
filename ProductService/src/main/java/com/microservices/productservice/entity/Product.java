package com.microservices.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "Products")
public class Product extends BaseEntity implements Serializable {

    private String name;
    private String category;
    private String photoUrl;
    private String description;
    private Double price;
    private Double weight;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProductDetail productDetail;
}
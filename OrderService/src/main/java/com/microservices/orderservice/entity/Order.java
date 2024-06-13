package com.microservices.orderservice.entity;

import com.microservices.orderservice.dto.response.external.ProductResponseDto;
import com.microservices.orderservice.enums.OrderStatus;
import com.microservices.orderservice.exceptionHandling.OrderException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Table(name = "Orders")
@Entity
@Getter
@Setter
public class Order extends BaseEntity implements Serializable {

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private Set<OrderProduct> orderProducts;

    private String orderNumber;

    private String description;

    private double shippingCost;

    private OrderStatus status;

    private long userId;
    private BigDecimal price;


}

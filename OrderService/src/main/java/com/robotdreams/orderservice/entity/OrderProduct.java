package com.robotdreams.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "OrderProducts")
@Entity
@Getter
@Setter
public class OrderProduct extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private long productId;
    private int quantity;
    private BigDecimal price;
}

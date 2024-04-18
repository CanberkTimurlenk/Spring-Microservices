package com.robotdreams.basketservice.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@RedisHash("CartItems")
public class CartItem implements Serializable {

    @Id
    private String id;
    private int quantity;
    private long productId;
    private String discountCode;
}

package com.microservices.basketservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RedisHash("ShoppingCarts")
public class ShoppingCart implements Serializable {

    @Id
    private long userId;
    private String discountCode;
    private List<CartItem> items;
}
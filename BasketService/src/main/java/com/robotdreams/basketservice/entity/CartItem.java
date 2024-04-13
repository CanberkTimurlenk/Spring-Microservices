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
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int quantity;
    private BigDecimal unitPrice;
    private long productId;
    private String discountCode;


    //@ManyToOne
    //@JoinColumn(name = "cart_id")
    private ShoppingCart cart;

}

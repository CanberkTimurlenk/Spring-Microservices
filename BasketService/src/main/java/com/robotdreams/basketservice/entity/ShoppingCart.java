package com.robotdreams.basketservice.entity;

//import jakarta.persistence.*;
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
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String discountCode;
    private BigDecimal discountAmount;
    private BigDecimal cartTotalAmount;

    //@OneToMany(mappedBy = "cart",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CartItem> items;

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getUnitPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getDiscountedPrice()
    {
        return getTotalPrice().subtract(discountAmount);
    }
}
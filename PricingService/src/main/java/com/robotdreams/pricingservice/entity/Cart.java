package com.robotdreams.pricingservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Cart
{
    private long userId;
    private List<Product> products;


    public BigDecimal calculateCartTotal(){
        return products.stream()
                .map(Product::getUnitPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

package com.robotdreams.pricingservice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
public class CartItem {

    private long productId;
    private BigDecimal unitPrice;
    private String discountCode;
    private int quantity;
    private BigDecimal itemTotalPrice;

    private CartItem(int quantity, long productId, BigDecimal unitPrice, String discountCode) {
        this.quantity = quantity;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.discountCode = discountCode;
    }

    @Builder(builderMethodName = "builder")
    public static CartItem newCartItem(long productId, BigDecimal unitPrice, String discountCode, int quantity) {
        CartItem cartItem = new CartItem(quantity, productId, unitPrice, discountCode);
        cartItem.calculateItemTotalPrice();
        return cartItem;
    }

    private void calculateItemTotalPrice() {
        itemTotalPrice = unitPrice.multiply(new BigDecimal(quantity));
    }
}
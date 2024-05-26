package com.microservices.pricingservice.entity;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class ContainerItem {

    private long productId;
    private BigDecimal unitPrice;
    private String discountCode;
    private int quantity;
    private BigDecimal itemTotalPrice;

    private ContainerItem(int quantity, long productId, BigDecimal unitPrice, String discountCode) {
        this.quantity = quantity;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.discountCode = discountCode;
    }

    @Builder(builderMethodName = "builder")
    public static ContainerItem newCartItem(long productId, BigDecimal unitPrice, String discountCode, int quantity) {
        ContainerItem cartItem = new ContainerItem(quantity, productId, unitPrice, discountCode);
        cartItem.calculateItemTotalPrice();
        return cartItem;
    }

    private void calculateItemTotalPrice() {
        itemTotalPrice = unitPrice.multiply(new BigDecimal(quantity));
    }
}
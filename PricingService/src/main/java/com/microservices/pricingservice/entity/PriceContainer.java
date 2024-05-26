package com.microservices.pricingservice.entity;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class PriceContainer {

    private final long userId;
    private final List<ContainerItem> containerItems;
    private final String discountCode;
    private BigDecimal cartTotalPrice;
    private final boolean isPremiumOrder;

    private PriceContainer(long userId, List<ContainerItem> containerItems, String discountCode, boolean isPremiumOrder) {
        this.userId = userId;
        this.containerItems = containerItems;
        this.discountCode = discountCode;
        this.isPremiumOrder = isPremiumOrder;
    }

    @Builder(builderMethodName = "builder")
    public static PriceContainer newCart(long userId, List<ContainerItem> containerItems,
                                         String discountCode, boolean isPremiumCart) {

        PriceContainer cart = new PriceContainer(userId, containerItems, discountCode, isPremiumCart);
        cart.calculateCartTotalPrice();

        return cart;
    }

    private void calculateCartTotalPrice() {
        BigDecimal price = containerItems.stream().map(ContainerItem::getItemTotalPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);

        if (isPremiumOrder) {
            this.cartTotalPrice = calculatePremiumPrice(price);
            return;
        }

        this.cartTotalPrice = price;
    }

    private BigDecimal calculatePremiumPrice(BigDecimal price) {

        BigDecimal discountMultiplier = new BigDecimal("0.95");
        //TODO: DiscountMultiplier could be a constant

        return price.multiply(discountMultiplier);
    }
}

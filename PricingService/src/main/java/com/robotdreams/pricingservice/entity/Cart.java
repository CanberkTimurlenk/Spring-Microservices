package com.robotdreams.pricingservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Cart {

    private long userId;
    private List<CartItem> cartItems;
    private String discountCode;
    private BigDecimal cartTotalPrice;
    private boolean isPremiumCart;

    private Cart(long userId, List<CartItem> cartItems, String discountCode, boolean isPremiumCart) {
        this.userId = userId;
        this.cartItems = cartItems;
        this.discountCode = discountCode;
        this.isPremiumCart = isPremiumCart;
    }

    @Builder(builderMethodName = "builder")
    public static Cart newCart(long userId, List<CartItem> cartItems,
                               String discountCode, boolean isPremiumCart) {

        Cart cart = new Cart(userId, cartItems, discountCode, isPremiumCart);
        cart.calculateCartTotalPrice();

        return cart;
    }

    private void calculateCartTotalPrice() {
        BigDecimal price = cartItems.stream().map(CartItem::getItemTotalPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);

        if (isPremiumCart) {
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

package com.robotdreams.pricingservice.dto.cart;

import java.util.List;


public record PricedCart(long userId,
                         List<PricedProduct> products) {

}

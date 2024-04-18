package com.robotdreams.pricingservice.dto.cart;


import java.math.BigDecimal;

public record PricedProduct(long productId,
                            int quantity,
                            BigDecimal unitPrice,
                            long discountCode) {


}

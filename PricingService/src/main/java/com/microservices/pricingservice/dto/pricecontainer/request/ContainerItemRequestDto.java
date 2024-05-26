package com.microservices.pricingservice.dto.pricecontainer.request;


import java.math.BigDecimal;

public record ContainerItemRequestDto(long productId,
                                      int quantity,
                                      BigDecimal unitPrice,
                                      String discountCode) {
}
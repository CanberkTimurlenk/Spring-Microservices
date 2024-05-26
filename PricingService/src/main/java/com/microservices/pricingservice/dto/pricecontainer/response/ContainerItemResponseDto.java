package com.microservices.pricingservice.dto.pricecontainer.response;

import java.math.BigDecimal;

public record ContainerItemResponseDto(long productId,
                                       int quantity,
                                       BigDecimal itemTotalPrice,
                                       BigDecimal unitPrice) {

}
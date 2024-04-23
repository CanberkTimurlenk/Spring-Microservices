package com.robotdreams.pricingservice.dto.pricecontainer.response;

import java.math.BigDecimal;
import java.util.List;

public record PriceContainerResponseDto(long userId,
                                        List<ContainerItemResponseDto> containerItems, BigDecimal cartTotalPrice,
                                        boolean isPremiumCart) {

}

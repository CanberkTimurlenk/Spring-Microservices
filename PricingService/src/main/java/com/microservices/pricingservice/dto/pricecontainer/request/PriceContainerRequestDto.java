package com.microservices.pricingservice.dto.pricecontainer.request;

import java.util.List;


public record PriceContainerRequestDto(long userId,
                                       List<ContainerItemRequestDto> containerItems, String discountCode) {

}

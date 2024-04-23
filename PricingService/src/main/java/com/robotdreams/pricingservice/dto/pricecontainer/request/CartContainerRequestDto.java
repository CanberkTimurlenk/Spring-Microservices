package com.robotdreams.pricingservice.dto.pricecontainer.request;

import java.util.List;


public record CartContainerRequestDto(long userId,
                                      List<ContainerItemRequestDto> containerItems, String discountCode) {

}

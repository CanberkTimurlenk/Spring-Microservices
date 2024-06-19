package com.microservices.orderservice.dto.request;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public record OrderRequestDto(Set<ProductRequestDto> productRequestDtoSet,
                              String description, Long userId) implements Serializable {
}
package com.robotdreams.orderservice.dto.request;

import java.io.Serializable;
import java.util.List;

public record OrderRequestDto(List<Long> productIdList,
                              String description, Long userId) implements Serializable {
}
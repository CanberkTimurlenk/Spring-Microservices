package com.laba.mobilemarket.order.dto;

import java.io.Serializable;
import java.util.List;

public record OrderRequestDto(List<Long> productIdList,
                              String description, Long userId) implements Serializable {
}
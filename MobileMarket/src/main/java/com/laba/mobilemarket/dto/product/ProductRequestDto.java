package com.laba.mobilemarket.dto.product;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ProductRequestDto(String name, String category,
                                String photoUrl, String description, Double price,
                                ProductDetailRequestDto productDetail) implements Serializable {
}
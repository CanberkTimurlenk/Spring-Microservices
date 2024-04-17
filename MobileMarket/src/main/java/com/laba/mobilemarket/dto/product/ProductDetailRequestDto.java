package com.laba.mobilemarket.dto.product;

import java.io.Serializable;


public record ProductDetailRequestDto(String productInfo, String productSerialNumber) implements Serializable {
}
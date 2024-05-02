package com.laba.mobilemarket.product.dto;

import java.io.Serializable;


public record ProductDetailRequestDto(String productInfo, String productSerialNumber) implements Serializable {
}
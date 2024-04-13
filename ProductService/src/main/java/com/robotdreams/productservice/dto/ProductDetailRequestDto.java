package com.robotdreams.productservice.dto;

import java.io.Serializable;


public record ProductDetailRequestDto(String productInfo, String productSerialNumber) implements Serializable {
}
package com.microservices.productservice.dto;

public record ProductResponseDto(String name, String category, String photoUrl, String price) {
}
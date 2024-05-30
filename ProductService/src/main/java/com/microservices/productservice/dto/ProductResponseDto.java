package com.microservices.productservice.dto;

public record ProductResponseDto(Long id, String name, String category, String photoUrl, String price) {
}
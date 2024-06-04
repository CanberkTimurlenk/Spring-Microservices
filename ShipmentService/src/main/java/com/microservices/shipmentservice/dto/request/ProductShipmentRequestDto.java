package com.microservices.shipmentservice.dto.request;


public record ProductShipmentRequestDto(long productId, long quantity) {
}

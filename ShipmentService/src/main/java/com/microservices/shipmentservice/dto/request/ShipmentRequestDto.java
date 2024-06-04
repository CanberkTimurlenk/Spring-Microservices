package com.microservices.shipmentservice.dto.request;

import java.util.List;

public record ShipmentRequestDto(
        List<ProductShipmentRequestDto> productShipments
    ) {
}

package com.microservices.shipmentservice.event.shipmentcancelled;

import com.microservices.shipmentservice.dto.request.ProductShipmentRequestDto;

import java.util.Date;
import java.util.List;

public record ShipmentCancelledEvent(long orderId, List<ProductShipmentRequestDto> productShipments, Date createdDate) {
}
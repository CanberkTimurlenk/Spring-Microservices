package com.microservices.inventoryservice.event.shipmentcancelled;

import java.util.Date;
import java.util.List;

public record ShipmentCancelledEvent(long orderId, List<ProductShipmentRequestDto> productShipments, Date createdDate) {
}

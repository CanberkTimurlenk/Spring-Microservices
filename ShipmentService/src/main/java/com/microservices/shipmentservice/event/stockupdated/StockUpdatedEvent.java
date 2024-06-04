package com.microservices.shipmentservice.event.stockupdated;

import java.util.Date;
import java.util.List;

public record StockUpdatedEvent(List<InventoryProduct> products, Date updatedDate) {
}

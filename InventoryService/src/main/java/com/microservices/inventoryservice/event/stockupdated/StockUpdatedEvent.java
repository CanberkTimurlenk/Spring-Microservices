package com.microservices.inventoryservice.event.stockupdated;

import java.util.Date;
import java.util.List;

public record StockUpdatedEvent(long orderId, List<InventoryProduct> products, Date updatedDate) {
}

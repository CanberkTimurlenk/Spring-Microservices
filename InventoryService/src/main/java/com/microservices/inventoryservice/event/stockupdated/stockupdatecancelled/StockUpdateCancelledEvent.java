package com.microservices.inventoryservice.event.stockupdated.stockupdatecancelled;

import com.microservices.inventoryservice.event.stockupdated.InventoryProduct;

import java.util.Date;
import java.util.List;

public record StockUpdateCancelledEvent(long orderId, List<InventoryProduct> products, Date createdDate) {
}

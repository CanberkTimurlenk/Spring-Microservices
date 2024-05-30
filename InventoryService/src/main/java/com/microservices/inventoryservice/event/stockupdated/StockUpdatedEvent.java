package com.microservices.inventoryservice.event.stockupdated;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;


public record StockUpdatedEvent(Set<InventoryProduct> products, Date updatedDate) {
}

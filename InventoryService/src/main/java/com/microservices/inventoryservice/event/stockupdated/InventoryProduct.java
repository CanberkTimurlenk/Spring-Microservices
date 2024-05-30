package com.microservices.inventoryservice.event.stockupdated;

public record InventoryProduct( long productId,
         long initialStockAmount,
         long finalStockAmount)  {

}

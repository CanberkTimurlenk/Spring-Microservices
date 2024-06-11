package com.microservices.inventoryservice.exceptionhandling;

public class InventoryException extends BusinessException {

    public InventoryException(long productId) {
        super("Insufficient stock for product Id: "
                + productId);
    }
}

package com.microservices.inventoryservice.exceptionHandling;

public class OrderException extends BusinessException {
    public OrderException(String message) {
        super(message);
    }

}

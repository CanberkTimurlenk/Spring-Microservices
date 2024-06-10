package com.microservices.inventoryservice.exceptionhandling;

public class OrderException extends BusinessException {
    public OrderException(String message) {
        super(message);
    }

}

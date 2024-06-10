package com.microservices.inventoryservice.exceptionhandling;

public class ProductException extends BusinessException {

    public ProductException(String message) {
        super(message);
    }
}

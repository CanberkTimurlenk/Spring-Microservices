package com.microservices.inventoryservice.exceptionHandling;

public class ProductException extends BusinessException {

    public ProductException(String message) {
        super(message);
    }
}

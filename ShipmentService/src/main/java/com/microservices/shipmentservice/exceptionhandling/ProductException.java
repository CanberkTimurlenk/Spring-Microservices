package com.microservices.shipmentservice.exceptionhandling;

public class ProductException extends BusinessException {

    public ProductException(String message) {
        super(message);
    }
}

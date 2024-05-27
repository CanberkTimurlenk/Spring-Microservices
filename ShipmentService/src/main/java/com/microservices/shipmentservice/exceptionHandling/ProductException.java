package com.microservices.shipmentservice.exceptionHandling;

public class ProductException extends BusinessException {

    public ProductException(String message) {
        super(message);
    }
}

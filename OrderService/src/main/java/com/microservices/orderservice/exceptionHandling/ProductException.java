package com.microservices.orderservice.exceptionHandling;

public class ProductException extends BusinessException {

    public ProductException(String message) {
        super(message);
    }
}

package com.microservices.productservice.exceptionHandling;

public class ProductException extends BusinessException {

    public ProductException(String message) {
        super(message);
    }
}

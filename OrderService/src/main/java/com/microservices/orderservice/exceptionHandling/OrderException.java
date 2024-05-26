package com.microservices.orderservice.exceptionHandling;

public class OrderException extends BusinessException {
    public OrderException(String message) {
        super(message);
    }

}

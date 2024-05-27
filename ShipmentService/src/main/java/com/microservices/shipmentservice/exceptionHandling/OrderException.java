package com.microservices.shipmentservice.exceptionHandling;

public class OrderException extends BusinessException {
    public OrderException(String message) {
        super(message);
    }

}

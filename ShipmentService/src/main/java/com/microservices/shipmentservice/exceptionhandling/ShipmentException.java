package com.microservices.shipmentservice.exceptionhandling;

public class ShipmentException extends BusinessException {
    public ShipmentException(String message) {
        super(message);
    }

}

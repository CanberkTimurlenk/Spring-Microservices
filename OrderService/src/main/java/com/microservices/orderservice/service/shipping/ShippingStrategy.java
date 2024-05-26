package com.microservices.orderservice.service.shipping;

public interface ShippingStrategy {

    public double calculate(double weight);
}

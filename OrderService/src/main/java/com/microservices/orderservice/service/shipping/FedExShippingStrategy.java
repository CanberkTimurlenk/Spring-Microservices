package com.microservices.orderservice.service.shipping;

public class FedExShippingStrategy implements ShippingStrategy {
    @Override
    public double calculate(double weight) {
        return weight / 4 + 20;
    }
}

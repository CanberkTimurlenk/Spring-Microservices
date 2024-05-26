package com.microservices.orderservice.service.shipping;

public class YurticiShippingStrategy implements ShippingStrategy {
    @Override
    public double calculate(double weight) {
        return weight / 2 + 5;
    }
}

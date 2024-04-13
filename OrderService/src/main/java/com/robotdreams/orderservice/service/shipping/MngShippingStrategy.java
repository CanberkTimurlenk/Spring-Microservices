package com.robotdreams.orderservice.service.shipping;

public class MngShippingStrategy implements ShippingStrategy {
    @Override
    public double calculate(double weight) {
        return weight / 3 + 12;
    }
}

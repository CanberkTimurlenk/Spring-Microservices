package com.robotdreams.orderservice.service.shipping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShippingCostCalculator {
    private final ShippingStrategy shippingStrategy;

    public double getCost(double weight) {
        return shippingStrategy.calculate(weight);
    }
}

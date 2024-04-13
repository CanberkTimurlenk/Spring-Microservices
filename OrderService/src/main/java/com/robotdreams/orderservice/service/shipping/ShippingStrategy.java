package com.robotdreams.orderservice.service.shipping;

public interface ShippingStrategy {

    public double calculate(double weight);
}

package com.robotdreams.orderservice.customFunctions;

@FunctionalInterface
public interface OrderCreatedSmsFunction {
    String replace(String template, String name, String email, String orderNumber);
}

package com.microservices.orderservice.customFunctions;

@FunctionalInterface
public interface OrderCreatedSmsFunction {
    String replace(String template, String name, String email, String orderNumber);
}
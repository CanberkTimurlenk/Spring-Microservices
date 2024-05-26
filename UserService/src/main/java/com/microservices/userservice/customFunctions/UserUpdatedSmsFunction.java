package com.microservices.userservice.customFunctions;

@FunctionalInterface
public interface UserUpdatedSmsFunction {
    String replace(String template, String firstname, String lastname);
}

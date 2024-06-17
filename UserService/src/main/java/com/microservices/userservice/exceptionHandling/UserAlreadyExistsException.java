package com.microservices.userservice.exceptionHandling;

public class UserAlreadyExistsException extends UserException{
    public UserAlreadyExistsException(String message) {
        super("A user with the email " + message + " already exists.");
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

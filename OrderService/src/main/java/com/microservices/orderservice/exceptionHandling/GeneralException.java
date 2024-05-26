package com.microservices.orderservice.exceptionHandling;

public class GeneralException extends RuntimeException{

    public GeneralException() {
    }

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

}

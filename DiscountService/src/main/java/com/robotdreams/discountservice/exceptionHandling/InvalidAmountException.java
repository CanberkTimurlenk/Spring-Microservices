package com.robotdreams.discountservice.exceptionHandling;

public class InvalidAmountException extends BusinessException{
    public InvalidAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}

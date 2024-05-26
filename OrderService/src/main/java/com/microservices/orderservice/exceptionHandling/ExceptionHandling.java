package com.microservices.orderservice.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> userExceptionHandler(UserException userException) {
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> orderExceptionHandler(ProductException productException) {
        return new ResponseEntity<>(productException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
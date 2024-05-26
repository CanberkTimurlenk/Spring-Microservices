package com.microservices.discountservice.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(BusinessException businessException) {
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

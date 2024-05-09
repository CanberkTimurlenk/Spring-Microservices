package com.robotdreams.orderservice.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> businessExceptionHandler(UserException userException) {
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

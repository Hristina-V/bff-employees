package com.academy.sirma.bff.employees.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handle(UnauthorizedException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}

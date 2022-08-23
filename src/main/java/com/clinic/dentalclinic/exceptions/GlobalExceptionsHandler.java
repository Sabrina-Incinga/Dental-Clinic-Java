package com.clinic.dentalclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> ResourceNotFoundHandler(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested resource was not found: %s \n %s".formatted(exception.getMessage(), exception.getStackTrace()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> BadRequestHandler(BadRequestException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}

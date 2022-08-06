package com.example.car_driver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            CarAlreadyInUseException.class,
            EntityNotFoundException.class
            , InstructionsDomainException.class})

    public ResponseEntity<Object> handlerException(Exception e) {
        ApiErrorObject apiErrorObject = new ApiErrorObject(e.getMessage(),  HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiErrorObject, HttpStatus.BAD_REQUEST);

    }
}

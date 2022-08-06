package com.example.car_driver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CarAlreadyInUseException extends Exception {

    public CarAlreadyInUseException(String message) {
        super(message);
    }
}

package com.example.car_driver.service;

import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Car;
import com.example.car_driver.domain_object.Driver;
import com.example.car_driver.exception.CarAlreadyInUseException;
import com.example.car_driver.exception.EntityNotFoundException;
import com.example.car_driver.exception.InstructionsDomainException;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver getDriver(String username) throws EntityNotFoundException;

    Driver getDriver(Long id) throws EntityNotFoundException;

    void createDriver(Driver driver);

    List<Driver> allDriver();

    void setStatus(Status status ,Long driverId) throws EntityNotFoundException;

    List<Driver> findByStatus(Status status);

    void deleteDriver(Long id) throws EntityNotFoundException;

    void selectCar(Long id, String licensePlate) throws EntityNotFoundException, InstructionsDomainException, CarAlreadyInUseException;
    Boolean checkObject(Optional object);
}

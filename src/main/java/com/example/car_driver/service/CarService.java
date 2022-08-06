package com.example.car_driver.service;

import com.example.car_driver.domain_enum.EngineType;
import com.example.car_driver.domain_object.Car;
import com.example.car_driver.exception.CarAlreadyInUseException;
import com.example.car_driver.exception.EntityNotFoundException;
import com.example.car_driver.exception.InstructionsDomainException;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car findCar(String license_plate) throws EntityNotFoundException;

    List<Car> findCarsByManufacturer(String manufacturer) ;

    List<Car> findCarsByEngineType(EngineType engineType) throws EntityNotFoundException;

    void createCar(Car car);

    void deleteCar(Long id) throws EntityNotFoundException;

    Boolean addDriver(Long id, String license_plate) throws CarAlreadyInUseException, EntityNotFoundException, InstructionsDomainException;;

    void addDriver(Long DriverId, Long CarId) throws CarAlreadyInUseException;

    void deleteDriver(Long DriverId, String license_plate) throws EntityNotFoundException, InstructionsDomainException;

    Boolean checkObject(Optional object);


}

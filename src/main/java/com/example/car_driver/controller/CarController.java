package com.example.car_driver.controller;

import com.example.car_driver.domain_enum.EngineType;
import com.example.car_driver.domain_object.Car;
import com.example.car_driver.exception.CarAlreadyInUseException;
import com.example.car_driver.exception.EntityNotFoundException;
import com.example.car_driver.exception.InstructionsDomainException;
import com.example.car_driver.service_implementation.CarServiceImp;
import com.example.car_driver.service_implementation.DriverServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cars")
public class CarController {

    CarServiceImp carServiceImp;
    DriverServiceImp driverServiceImp;


    @Autowired
    public CarController(CarServiceImp carServiceImp, DriverServiceImp driverServiceImp) {
        this.carServiceImp = carServiceImp;
        this.driverServiceImp = driverServiceImp;
    }
    @ResponseBody
    @GetMapping("/{license_plate}")
    public Car getCar(@PathVariable String license_plate) throws EntityNotFoundException {
        return carServiceImp.findCar(license_plate);
    }
    @ResponseBody
    @GetMapping("/manufacturer/{manufacturer}")
    public List<Car> getCarsByManufacturer(@PathVariable String manufacturer){
        return carServiceImp.findCarsByManufacturer(manufacturer);
    }

    @ResponseBody
    @GetMapping("/engine_type")
    public List<Car> findCarsByEngineType(@RequestBody EngineType engineType) throws EntityNotFoundException {
        return carServiceImp.findCarsByEngineType(engineType);
    }

    @PostMapping()
    public void createCar(@RequestBody Car car){
        carServiceImp.createCar(car);
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable Long carId) throws EntityNotFoundException {
        carServiceImp.deleteCar(carId);
    }

    @PutMapping("/add_driver/{driverId}/{license_plate}")
    public void addDriver(@PathVariable Long driverId ,@PathVariable String license_plate) throws CarAlreadyInUseException, EntityNotFoundException, InstructionsDomainException {
        carServiceImp.addDriver(driverId,license_plate);
    }

    @PutMapping("/delete_driver/{driverId}/{license_plate}")
    public  void deleteDriver(@PathVariable Long driverId ,@PathVariable String license_plate ) throws EntityNotFoundException, InstructionsDomainException {
        carServiceImp.deleteDriver(driverId,license_plate);
    }
}

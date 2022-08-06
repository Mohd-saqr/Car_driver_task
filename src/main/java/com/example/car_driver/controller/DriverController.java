package com.example.car_driver.controller;

import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Driver;
import com.example.car_driver.exception.CarAlreadyInUseException;
import com.example.car_driver.exception.EntityNotFoundException;
import com.example.car_driver.exception.InstructionsDomainException;
import com.example.car_driver.service_implementation.CarServiceImp;
import com.example.car_driver.service_implementation.DriverServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/drivers")
public class DriverController {
    DriverServiceImp driverServiceImp;
    CarServiceImp carServiceImp;

    @Autowired
    public DriverController(DriverServiceImp driverServiceImp, CarServiceImp carServiceImp) {
        this.driverServiceImp = driverServiceImp;
        this.carServiceImp = carServiceImp;
    }

    @GetMapping("/{driverId}")
    @ResponseBody
    public Driver getDriver(@PathVariable Long driverId) throws EntityNotFoundException {
       return driverServiceImp.getDriver(driverId);
    }

    @PostMapping()
    public Driver createDriver(@RequestBody Driver driver ){
        driverServiceImp.createDriver(driver);
        return driver;
    }
    @GetMapping
    @ResponseBody
    public List<Driver> allDriver(){
        return driverServiceImp.allDriver();
    }

    @PutMapping("/set_status/{driverId}")
    public String setStatus(@PathVariable Long driverId ,@RequestBody Status status ) throws EntityNotFoundException {
        driverServiceImp.setStatus(status,driverId);
        return "status of driver with id = " + driverId +  "is set to = " + status.toString();
    }

    @GetMapping("/status")
    @ResponseBody
    public List<Driver> getDriverByStatus(@RequestBody Status status){
        return driverServiceImp.findByStatus(status);
    }

    @DeleteMapping("/{driverId}")
    public String deleteDriver(@PathVariable  Long driverId) throws EntityNotFoundException {
        driverServiceImp.deleteDriver(driverId);

        return "Driver with id =" + driverId + "is deleted";
    }

    @PutMapping("/select_car/{driverId}/{licensePlate}")
    public String selectCar(@PathVariable Long driverId ,@PathVariable String licensePlate) throws CarAlreadyInUseException, EntityNotFoundException, InstructionsDomainException {
        driverServiceImp.selectCar(driverId,licensePlate);
        return "car with licensePlate =" +licensePlate +"is selected by driver with " +
                "id= " +driverId;
    }



}

package com.example.car_driver.service_implementation;

import com.example.car_driver.data_access_objects.CarRepo;
import com.example.car_driver.data_access_objects.DriverRepo;
import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Car;
import com.example.car_driver.domain_object.Driver;
import com.example.car_driver.exception.CarAlreadyInUseException;
import com.example.car_driver.exception.EntityNotFoundException;
import com.example.car_driver.exception.InstructionsDomainException;
import com.example.car_driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImp implements DriverService {

    DriverRepo driverRepo;

    CarRepo carRepo;

    @Autowired
    public DriverServiceImp(DriverRepo driverRepo, CarRepo carRepo) {
        this.driverRepo = driverRepo;
        this.carRepo = carRepo;
    }


    @Override
    public Driver getDriver(String username) throws EntityNotFoundException {
        Optional<Driver> driver = driverRepo.findByUsername(username);
        if (checkObject(driver))
            throw new EntityNotFoundException("");

        return driver.get();
    }

    @Override
    public Driver getDriver(Long id) throws EntityNotFoundException {
        Optional<Driver>driver = driverRepo.findById(id);
        if (driver.isEmpty())
            throw new EntityNotFoundException("");
        return driver.get();
    }

    @Override
    public void createDriver(Driver driver) {
        driverRepo.save(driver);
    }

    @Override
    public List<Driver> allDriver() {
        return driverRepo.findAll();
    }

    @Override
    public void setStatus(Status status, Long driverId) throws EntityNotFoundException {
        Optional<Driver> driver = driverRepo.findById(driverId);
        if (checkObject(driver))
            throw new EntityNotFoundException("");

        driver.get().setStatus(status);

    }

    @Override
    public List<Driver> findByStatus(Status status) {

        return driverRepo.findByStatus(status);
    }


    @Override
    public void deleteDriver(Long id) throws EntityNotFoundException {
        Optional<Driver> driver = driverRepo.findById(id);
        if (checkObject(driver))
            throw new EntityNotFoundException("");
        driverRepo.delete(driver.get());
    }

    @Transactional
    @Override
    public void selectCar(Long id, String  licensePlate) throws EntityNotFoundException, InstructionsDomainException, CarAlreadyInUseException {
        Optional<Driver> driver = driverRepo.findById(id);
        Optional<Car> car = carRepo.findByLicensePlate(licensePlate);
        if (checkObject(car))
            throw new InstructionsDomainException("");
        if (car.get().getDriver() != null)
            throw new CarAlreadyInUseException("");
        if (checkObject(driver))
            throw new EntityNotFoundException("");
        if (driver.get().getStatus()!=Status.ONLINE)
            throw new InstructionsDomainException("driver is offline");

        driver.get().setCar(car.get());
        driverRepo.save(driver.get());

    }

    @Override
    public Boolean checkObject(Optional object) {

        return object.isEmpty();
    }
}

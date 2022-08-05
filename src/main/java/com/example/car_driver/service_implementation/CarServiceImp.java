package com.example.car_driver.service_implementation;

import com.example.car_driver.data_access_objects.CarRepo;
import com.example.car_driver.data_access_objects.DriverRepo;
import com.example.car_driver.domain_enum.EngineType;
import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Car;
import com.example.car_driver.domain_object.Driver;
import com.example.car_driver.exception.CarAlreadyInUseException;
import com.example.car_driver.exception.EntityNotFoundException;
import com.example.car_driver.exception.InstructionsDomainException;
import com.example.car_driver.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImp implements CarService {


    CarRepo carRepo;
    DriverRepo driverRepo;

    @Autowired
    public CarServiceImp(CarRepo carRepo, DriverRepo driverRepo) {
        this.carRepo = carRepo;
        this.driverRepo = driverRepo;
    }

    @Override
    public Car findCar(String license_plate) throws EntityNotFoundException {
        Optional<Car> carOptional = carRepo.findByLicensePlate(license_plate);
        if (carOptional.isEmpty())
            throw new EntityNotFoundException("car not found with license_plate ="+ license_plate) ;
        return carOptional.get();
    }

    @Override
    public List<Car> findCarsByManufacturer(String manufacturer)  {
        return findCarsByManufacturer(manufacturer);
    }

    @Override
    public List<Car> findCarsByEngineType(EngineType engineType) throws EntityNotFoundException {
        return carRepo.findCarsByEngineType(engineType);
    }

    @Override
    public void createCar(Car car) {
        carRepo.save(car);
    }

    @Override
    public void deleteCar(Long id) throws EntityNotFoundException {
        Optional<Car> car = carRepo.findById(id);
        if (car.isEmpty())
            throw new EntityNotFoundException("");

        carRepo.delete(car.get());


    }

    @Transactional
    @Override
    public Boolean addDriver(Long id, String license_plate) throws EntityNotFoundException, CarAlreadyInUseException, InstructionsDomainException {
        Optional<Car> car = carRepo.findByLicensePlate(license_plate);
        Optional<Driver> driver = driverRepo.findById(id);
        if (car.isEmpty())
            throw new EntityNotFoundException("");
        if (driver.isEmpty())
            throw new EntityNotFoundException("");
        if (car.get().getDriver() != null)
            throw new CarAlreadyInUseException("");
        if (driver.get().getStatus() != Status.ONLINE)
            throw new InstructionsDomainException("");

        car.get().setDriver(driver.get());
        carRepo.save(car.get());


        return null;
    }

    @Override
    public void addDriver(Long DriverId, Long CarId) throws CarAlreadyInUseException {

    }

    @Transactional
    @Override
    public void deleteDriver(Long DriverId, String license_plate) throws EntityNotFoundException, InstructionsDomainException {
        Optional<Car> car = carRepo.findByLicensePlate(license_plate);
        Optional<Driver> driver = driverRepo.findById(DriverId);
        if (checkObject(car))
            throw new EntityNotFoundException("");

        if (checkObject(driver))
            throw new EntityNotFoundException("");

        if (car.get().getDriver() == null)
            throw new InstructionsDomainException("");

        if (!car.get().getDriver().getId().equals(driver.get().getId()))
            throw new InstructionsDomainException("");

        car.get().setDriver(null);
        driver.get().setCar(null);
        carRepo.save(car.get());
        driverRepo.save(driver.get());

    }

    @Override
    public Boolean checkObject(Optional object) {
        return object.isEmpty();

    }
}

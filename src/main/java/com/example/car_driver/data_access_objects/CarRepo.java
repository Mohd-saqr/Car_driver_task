package com.example.car_driver.data_access_objects;

import com.example.car_driver.domain_enum.EngineType;
import com.example.car_driver.domain_object.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    Optional<Car> findByLicensePlate(String license_plate);

    Optional<Car> findById(Long id);

    @Query("SELECT c FROM Car c WHERE c.manufacturer = ?1")
    List<Car> findCarsByManufacturer(String manufacturer);

    @Query("SELECT c FROM Car c WHERE c.engineType = ?1")
    List<Car> findCarsByEngineType(EngineType engineType);


}

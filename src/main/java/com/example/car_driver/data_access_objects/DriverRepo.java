package com.example.car_driver.data_access_objects;

import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepo extends JpaRepository<Long, Driver> {
    Driver findByUserName(String username);

    List<Driver> findByStatus(Status status);
}

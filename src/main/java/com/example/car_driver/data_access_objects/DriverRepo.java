package com.example.car_driver.data_access_objects;

import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUsername(String username);

    List<Driver> findByStatus(Status status);
}

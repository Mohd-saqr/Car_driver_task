package com.example.car_driver.api_fillter;

import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaStatus implements Criteria{
    @Override
    public List<Driver> meetCriteria(List<Driver> drivers) {
        return drivers.stream().filter(driver -> driver.getStatus().equals(Status.ONLINE))
                .collect(Collectors.toList());
    }
}

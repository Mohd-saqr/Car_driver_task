package com.example.car_driver.api_fillter;

import com.example.car_driver.domain_object.Driver;

import java.util.List;

public interface Criteria {

     List<Driver> meetCriteria(List<Driver> drivers);
}

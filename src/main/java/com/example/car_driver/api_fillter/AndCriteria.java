package com.example.car_driver.api_fillter;

import com.example.car_driver.domain_object.Driver;

import java.util.List;

public class AndCriteria implements Criteria{

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Driver> meetCriteria(List<Driver> drivers) {
        List<Driver> firstCriteriaPersons = criteria.meetCriteria(drivers);

        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Criteria getOtherCriteria() {
        return otherCriteria;
    }

    public void setOtherCriteria(Criteria otherCriteria) {
        this.otherCriteria = otherCriteria;
    }
}

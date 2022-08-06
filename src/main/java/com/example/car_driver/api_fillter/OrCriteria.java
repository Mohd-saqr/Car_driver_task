package com.example.car_driver.api_fillter;

import com.example.car_driver.domain_object.Driver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OrCriteria implements Criteria{
    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Driver> meetCriteria(List<Driver> drivers) {
        List<Driver> firstCriteriaItems = criteria.meetCriteria(drivers);
        List<Driver> otherCriteriaItems = otherCriteria.meetCriteria(drivers);
        firstCriteriaItems.addAll(otherCriteriaItems);

        return new ArrayList<>(new HashSet<>(otherCriteriaItems));
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

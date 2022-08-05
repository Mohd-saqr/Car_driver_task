package com.example.car_driver.security;

import com.example.car_driver.data_access_objects.DriverRepo;
import com.example.car_driver.domain_object.Driver;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DriverDetailsService implements UserDetailsService {
    DriverRepo driverRepo;
    DriverDetails driverDetails;

    public DriverDetailsService(DriverRepo driverRepo, DriverDetails driverDetails) {
        this.driverRepo = driverRepo;
        this.driverDetails = driverDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Driver> driverOptional = driverRepo.findByUsername(username);
        return driverOptional.map(DriverDetails::new).orElseThrow(() -> new EntityNotFoundException("Username or password incorrect "));
    }
}

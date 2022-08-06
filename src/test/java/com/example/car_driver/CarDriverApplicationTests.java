package com.example.car_driver;


import com.example.car_driver.data_access_objects.CarRepo;
import com.example.car_driver.data_access_objects.DriverRepo;
import com.example.car_driver.domain_enum.EngineType;
import com.example.car_driver.domain_enum.Status;
import com.example.car_driver.domain_object.Car;
import com.example.car_driver.domain_object.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class CarDriverApplicationTests {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MockMvc mockMvc;
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    CarRepo carRepo;

    private final String url = "/api/drivers";

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        driverRepo.deleteAll();
        carRepo.deleteAll();
    }

    @Test
    void CreateDriver() throws Exception {
        Driver driver = Driver.builder()
                .username("mohammed3")
                .password("0000")
                .status(Status.ONLINE)
                .car(null)
                .date_created(ZonedDateTime.now())
                .build();
        ResultActions response = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)));

        response.andDo(print()).
                andExpect(status().is(200))
                .andExpect(jsonPath("$.username",
                        is(driver.getUsername())))
                .andExpect(jsonPath("$.status",
                        is(driver.getStatus().toString())));

    }


    @Test
    void getAllDriver() throws Exception {
        List<Driver> drivers = Arrays.asList(
                Driver.builder().username("khaled")
                        .car(null)
                        .date_created(ZonedDateTime.now())
                        .status(Status.OFFLINE)
                        .password("525252")
                        .build(),
                Driver.builder().username("hassan")
                        .car(null)
                        .date_created(ZonedDateTime.now())
                        .status(Status.OFFLINE)
                        .password("525252")
                        .build(),
                Driver.builder().username("mahmoud")
                        .car(null)
                        .date_created(ZonedDateTime.now())
                        .status(Status.OFFLINE)
                        .password("525252")
                        .build()
        );

        driverRepo.saveAll(drivers);
        ResultActions resultActions = mockMvc.perform(get(url));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(drivers.size())));


    }

    @Test
    void getDriverById() throws Exception {
        Driver driver = Driver.builder().username("khaled")
                .car(null)
                .date_created(ZonedDateTime.now())
                .status(Status.OFFLINE)
                .password("525252")
                .build();

        driverRepo.save(driver);

        ResultActions resultActions = mockMvc.perform(get(url + "/{driverId}", driver.getId()));

        resultActions.
                andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.username", is(driver.getUsername())))
                .andExpect(jsonPath("$.status", is(driver.getStatus().toString())));


    }

    /// if the driver is logout, and you need to set status to offline
    @Test
    void setStatus() throws Exception {
        Driver driver = Driver.builder().username("khaled")
                .car(null)
                .date_created(ZonedDateTime.now())
                .status(Status.ONLINE)
                .password("525252")
                .build();
        driverRepo.save(driver);

        Status status = Status.OFFLINE;


        ResultActions resultActions = mockMvc.
                perform(put(url + "/set_status/{driverId}",
                        driver.getId()).contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(status)));


        resultActions.andExpect(status().isOk())
                .andExpect(content().string("status of driver with id = " + driver.getId() + "is set to = " + status));

    }

    @Test
    void getAllDriverAsStatus() throws Exception {
        List<Driver> drivers = Arrays.asList(
                Driver.builder().username("khaled")
                        .car(null)
                        .date_created(ZonedDateTime.now())
                        .status(Status.ONLINE)
                        .password("525252")
                        .build(),
                Driver.builder().username("hassan")
                        .car(null)
                        .date_created(ZonedDateTime.now())
                        .status(Status.OFFLINE)
                        .password("525252")
                        .build(),
                Driver.builder().username("mahmoud")
                        .car(null)
                        .date_created(ZonedDateTime.now())
                        .status(Status.OFFLINE)
                        .password("525252")
                        .build()
        );

        driverRepo.saveAll(drivers);

        List<Driver> offlineDrivers = drivers
                .stream().filter(driver -> driver.getStatus().equals(Status.OFFLINE)).
                toList();

        ResultActions resultActions = mockMvc.perform(get(url + "/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Status.OFFLINE)));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(offlineDrivers.size())));
    }


    @Test
    void selectCar() throws Exception {
        Car car = Car.builder()
                .driver(null)
                .date_created(ZonedDateTime.now())
                .engineType(EngineType.ELECTRIC)
                .seat_count(5)
                .manufacturer("Opel")
                .convertible(true)
                .licensePlate("102030")
                .build();

        Driver driver = Driver.builder().username("khaled")
                .car(null)
                .date_created(ZonedDateTime.now())
                .status(Status.ONLINE)
                .password("525252")
                .build();
        driverRepo.save(driver);
        carRepo.save(car);

        ResultActions resultActions = mockMvc.
                perform(put(url + "/select_car/{driverId}/{licensePlate}", driver.getId(), car.getLicensePlate()));

        resultActions.andExpect(status().isOk())
                .andExpect(content().string("car with licensePlate =" + car.getLicensePlate() + "is selected by driver with " +
                        "id= " + driver.getId()));

    }


    /**
     * test invalid path
     */

    @Test
    void GetDriverWithInvalidId() throws Exception {
        long invalidId = 102L;
        ResultActions resultActions = mockMvc.perform(get(url + "/{driverId}", invalidId));

        resultActions.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.massage", is("Entity not found")));
    }


    @Test
    void selectCarWithOfflineDriver() throws Exception {
        Car car = Car.builder()
                .driver(null)
                .date_created(ZonedDateTime.now())
                .engineType(EngineType.ELECTRIC)
                .seat_count(5)
                .manufacturer("Opel")
                .convertible(true)
                .licensePlate("102030")
                .build();

        Driver driver = Driver.builder().username("khaled")
                .car(null)
                .date_created(ZonedDateTime.now())
                .status(Status.OFFLINE)
                .password("525252")
                .build();

        driverRepo.save(driver);
        carRepo.save(car);

        ResultActions resultActions = mockMvc.
                perform(put(url + "/select_car/{driverId}/{licensePlate}", driver.getId(), car.getLicensePlate()));

        resultActions.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.massage", is("driver is offline")));
    }


    @Test
    void selectCarIsAlradySelected() throws Exception {

        Driver driver = Driver.builder().username("khaled")
                .car(null)
                .date_created(ZonedDateTime.now())
                .status(Status.ONLINE)
                .password("525252")
                .build();
        Car car = Car.builder()
                .driver(null)
                .date_created(ZonedDateTime.now())
                .engineType(EngineType.ELECTRIC)
                .seat_count(5)
                .manufacturer("Opel")
                .convertible(true)
                .licensePlate("102030")
                .build();

        Driver driverTow = Driver.builder().username("hassan")
                .car(null)
                .date_created(ZonedDateTime.now())
                .status(Status.ONLINE)
                .password("525252")
                .build();
        driverRepo.save(driver);
        driverRepo.save(driverTow);
        carRepo.save(car);

        ResultActions resultActions = mockMvc.
                perform(put(url + "/select_car/{driverId}/{licensePlate}", driver.getId(), car.getLicensePlate()));

        ResultActions resultActionsTow = mockMvc.
                perform(put(url + "/select_car/{driverId}/{licensePlate}", driverTow.getId(), car.getLicensePlate()));

        resultActionsTow.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.massage", is("CAR ALREADY IN USE")));
    }

}

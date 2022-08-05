package com.example.car_driver.domain_object;
import com.example.car_driver.domain_enum.EngineType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(nullable = false)

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime date_created = ZonedDateTime.now();

    @Column(unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private int seat_count;

    @Column(nullable = false)
    private Boolean convertible;

    @Column(nullable = false)
    private String manufacturer;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "car")
    private Driver driver;

    public Car() {
    }

    public Car(String licensePlate, int seat_count, Boolean convertible, String manufacturer, EngineType engineType) {
        this.licensePlate = licensePlate;
        this.seat_count = seat_count;
        this.convertible = convertible;
        this.manufacturer = manufacturer;
        this.engineType = engineType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate_created() {
        return date_created;
    }

    public void setDate_created(ZonedDateTime date_created) {
        this.date_created = date_created;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String license_plate) {
        this.licensePlate = license_plate;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }
}

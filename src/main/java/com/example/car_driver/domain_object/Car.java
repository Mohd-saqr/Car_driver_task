package com.example.car_driver.domain_object;
import com.example.car_driver.domain_enum.EngineType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
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


}

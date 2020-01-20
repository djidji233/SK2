package rs.raf.sk.weatherinfo_service.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double lastMeasureValue;

    private LocalDateTime lastMeasureTime;

    @OneToMany(mappedBy = "city")
    private List<WeatherInfo> measurements = new ArrayList<>();

}

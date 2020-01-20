package domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherInfo {

    private Long id;

    private String city;

    private Double temp;

    private LocalDateTime dateTime;

}

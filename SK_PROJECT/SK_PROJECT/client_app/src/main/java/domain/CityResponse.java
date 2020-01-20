package domain;

import lombok.Data;

import java.util.List;

@Data
public class CityResponse {

    private Long id;

    private String name;

    private List<WeatherInfo> measurements;

}

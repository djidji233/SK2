package service;

import domain.CityResponse;
import domain.WeatherInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherService {

    List<WeatherInfo> findCurrentWeatherData();

    CityResponse findByCityAndTime(Long cityId, LocalDateTime from, LocalDateTime to);

}

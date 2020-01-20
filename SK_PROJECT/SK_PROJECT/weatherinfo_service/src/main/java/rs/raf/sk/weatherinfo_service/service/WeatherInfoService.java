package rs.raf.sk.weatherinfo_service.service;

import rs.raf.sk.weatherinfo_service.domain.dto.CityResponse;
import rs.raf.sk.weatherinfo_service.domain.dto.WeatherInfoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherInfoService {

    List<WeatherInfoResponse> currentInfo();

    CityResponse findByCityAndTimeBetween(Long cityId, LocalDateTime from, LocalDateTime to);

    void updateStore();

}

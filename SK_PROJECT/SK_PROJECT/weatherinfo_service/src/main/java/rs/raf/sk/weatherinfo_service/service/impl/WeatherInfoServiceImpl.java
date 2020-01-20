package rs.raf.sk.weatherinfo_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.raf.sk.weatherinfo_service.dao.CityDao;
import rs.raf.sk.weatherinfo_service.dao.WeatherInfoDao;
import rs.raf.sk.weatherinfo_service.domain.City;
import rs.raf.sk.weatherinfo_service.domain.WeatherInfo;
import rs.raf.sk.weatherinfo_service.domain.dto.CityResponse;
import rs.raf.sk.weatherinfo_service.domain.dto.ForecastResponse;
import rs.raf.sk.weatherinfo_service.domain.dto.WeatherInfoResponse;
import rs.raf.sk.weatherinfo_service.service.OpenWeatherService;
import rs.raf.sk.weatherinfo_service.service.WeatherInfoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherInfoServiceImpl implements WeatherInfoService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherInfoService.class);

    private final Double CELSIUS_KELVIN_DIFF = 273.15;

    @Value("${app.key}")
    private String APP_KEY;

    private final CityDao cityDao;

    private final WeatherInfoDao weatherInfoDao;

    private final OpenWeatherService openWeatherService;

    @Override
    public List<WeatherInfoResponse> currentInfo() {
        return cityDao.findAll().stream()
                .map(city -> new WeatherInfoResponse(city.getId(), city.getName(), city.getLastMeasureValue(), city.getLastMeasureTime()))
                .collect(Collectors.toList());
    }

    @Override
    public CityResponse findByCityAndTimeBetween(Long cityId, LocalDateTime from, LocalDateTime to) {
        City city = cityDao.findById(cityId).orElseThrow(RuntimeException::new);

        List<WeatherInfoResponse> measurements = weatherInfoDao.findByCityAndDateTimeBetween(city, from, to).stream()
                .map(item -> new WeatherInfoResponse(item.getId(),null, item.getValue(), item.getDateTime())).collect(Collectors.toList());

        return new CityResponse(city.getId(), city.getName(), measurements);
    }

    @Override
    public void updateStore() {
        final LocalDateTime measureTime = LocalDateTime.now();
        cityDao.findAll().forEach(city -> {
            var response = openWeatherService.fetchByCity(city.getName(), APP_KEY);
            if(response.isEmpty()) return;
            ForecastResponse forecastResponse = response.get().getMain();
            double temp = forecastResponse.getTemp() - CELSIUS_KELVIN_DIFF;
            WeatherInfo weatherInfo = WeatherInfo.builder()
                    .city(city).dateTime(measureTime).value(temp).build();
            logger.info("New weather info: {}", forecastResponse);
            weatherInfoDao.save(weatherInfo);
            city.setLastMeasureValue(temp);
            city.setLastMeasureTime(measureTime);
            cityDao.save(city);
        });
    }

}

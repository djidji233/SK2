package service.impl;

import com.google.gson.reflect.TypeToken;
import domain.CityResponse;
import domain.WeatherInfo;
import service.WeatherService;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherServiceImpl extends AbstractHttpService implements WeatherService {

    public WeatherServiceImpl() {
        super("http://localhost:8081/weatherInfo");
    }

    @Override
    public List<WeatherInfo> findCurrentWeatherData() {
        return get("current", new TypeToken<List<WeatherInfo>>(){}.getType());
    }

    @Override
    public CityResponse findByCityAndTime(Long cityId, LocalDateTime from, LocalDateTime to) {
        String uri = String.format("city/%s?from=%s&to=%s", cityId.toString(), from.toString(), to.toString());
        return get(uri, CityResponse.class);
    }

}

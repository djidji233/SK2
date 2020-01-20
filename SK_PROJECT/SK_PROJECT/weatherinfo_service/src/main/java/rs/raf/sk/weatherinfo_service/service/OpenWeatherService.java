package rs.raf.sk.weatherinfo_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.raf.sk.weatherinfo_service.domain.dto.OpenWeatherResponse;

import java.util.Optional;

@FeignClient(name = "weatherService", url = "https://api.openweathermap.org/data/2.5/")
public interface OpenWeatherService {

    @GetMapping("/weather")
    Optional<OpenWeatherResponse> fetchByCity(@RequestParam(name = "q") String city, @RequestParam(name = "APPID") String key);

}

package rs.raf.sk.weatherinfo_service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import rs.raf.sk.weatherinfo_service.domain.dto.CityResponse;
import rs.raf.sk.weatherinfo_service.domain.dto.WeatherInfoResponse;
import rs.raf.sk.weatherinfo_service.service.WeatherInfoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/weatherInfo")
@RequiredArgsConstructor
public class WeatherInfoApi {

    private final WeatherInfoService weatherInfoService;

    @GetMapping("/current")
    public List<WeatherInfoResponse> findCurrStates() {
        return weatherInfoService.currentInfo();
    }

    @GetMapping("/city/{cityId}")
    public CityResponse findByCityAndPeriod(@PathVariable Long cityId,
                                            @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                            @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return weatherInfoService.findByCityAndTimeBetween(cityId, from, to);
    }

}

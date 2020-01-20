package rs.sk.project.usersub_service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Primary
@FeignClient(name = "zuul", fallback = WeatherServiceFallback.class)
public interface WeatherService {

    @PostMapping("/city/checkExists")
    Optional<Boolean> checkCityExisting(@RequestParam String cityId);

}

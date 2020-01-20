package rs.sk.project.usersub_service.service.feign;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WeatherServiceFallback implements WeatherService {

    @Override
    public Optional<Boolean> checkCityExisting(String cityId) {
        return Optional.empty();
    }

}

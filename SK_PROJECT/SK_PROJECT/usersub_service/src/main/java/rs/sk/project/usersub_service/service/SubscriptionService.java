package rs.sk.project.usersub_service.service;

import rs.sk.project.usersub_service.domain.dto.UserResponse;
import rs.sk.project.usersub_service.domain.dto.WeatherItemDto;

import java.util.List;

public interface SubscriptionService {

    UserResponse subscribe(String email, String cityId);

    UserResponse unsubscribe(String email, String cityId);

    void sendNotifications(List<WeatherItemDto> data);

}

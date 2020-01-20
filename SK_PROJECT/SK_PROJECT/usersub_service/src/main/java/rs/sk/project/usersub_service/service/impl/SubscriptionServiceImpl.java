package rs.sk.project.usersub_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.sk.project.usersub_service.domain.User;
import rs.sk.project.usersub_service.domain.dto.MessageRequest;
import rs.sk.project.usersub_service.domain.dto.UserResponse;
import rs.sk.project.usersub_service.domain.dto.WeatherItemDto;
import rs.sk.project.usersub_service.service.MessageOutputChannel;
import rs.sk.project.usersub_service.service.SubscriptionService;
import rs.sk.project.usersub_service.service.UserService;
import rs.sk.project.usersub_service.util.Exceptions;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableBinding(MessageOutputChannel.class)
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserService userService;

    private final MessageOutputChannel messageOutputChannel;

    @Override
    public UserResponse subscribe(String email, String cityId) {
        User user = userService.findByEmail(email);
        if(user.getSubscriptions().contains(cityId))
            throw new Exceptions.IllegalStatException("You are already subscribed on this city!");
        user.getSubscriptions().add(cityId);
        return userService.update(user);
    }

    @Override
    public UserResponse unsubscribe(String email, String cityId) {
        User user = userService.findByEmail(email);
        if(!user.getSubscriptions().contains(cityId))
            throw new Exceptions.IllegalStatException("You aren't subscribed on this city!");
        user.getSubscriptions().remove(cityId);
        return userService.update(user);
    }

    @Async
    @Override
    public void sendNotifications(List<WeatherItemDto> data) {
        var dataGrid = data.stream().collect(Collectors.toMap(WeatherItemDto::getId, Function.identity()));
        userService.findAll().forEach(user -> {
            Map<String, Double> content = user.getSubscriptions()
                    .stream().filter(Predicate.not(dataGrid::containsKey))
                    .map(dataGrid::get).collect(Collectors.toMap(WeatherItemDto::getCity, WeatherItemDto::getValue));
            messageOutputChannel.output()
                    .send(MessageBuilder.withPayload(new MessageRequest(user.getEmail(), content)).build());
        });
    }

}

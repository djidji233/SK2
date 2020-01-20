package rs.sk.project.usersub_service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.sk.project.usersub_service.domain.dto.UserResponse;
import rs.sk.project.usersub_service.domain.dto.WeatherItemDto;
import rs.sk.project.usersub_service.service.SubscriptionService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionApi {

    private final SubscriptionService subscriptionService;

    @PostMapping("/{id}")
    public UserResponse subscribe(@PathVariable String id, Principal principal) {
        return subscriptionService.subscribe(principal.getName(), id);
    }

    @DeleteMapping("/{id}")
    public UserResponse unsubscribe(@PathVariable String id, Principal principal) {
        return subscriptionService.unsubscribe(principal.getName(), id);
    }

    @PostMapping("/sendNotifications")
    public void sendNotifications(@RequestBody List<WeatherItemDto> data) {
        subscriptionService.sendNotifications(data);
    }

}

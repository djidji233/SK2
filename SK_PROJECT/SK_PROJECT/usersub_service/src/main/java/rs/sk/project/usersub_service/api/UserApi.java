package rs.sk.project.usersub_service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sk.project.usersub_service.domain.dto.UserResponse;
import rs.sk.project.usersub_service.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/myInfo")
    public UserResponse findMyInfo(Principal principal) {
        return userService.findMyInfo(principal.getName());
    }

}

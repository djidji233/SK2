package rs.sk.project.usersub_service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.sk.project.usersub_service.domain.dto.LoginRequest;
import rs.sk.project.usersub_service.domain.dto.LoginResponse;
import rs.sk.project.usersub_service.domain.dto.UserRequest;
import rs.sk.project.usersub_service.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody UserRequest userRequest) {
        return authService.register(userRequest);
    }

    @GetMapping
    public String authorize(String token) {
        return authService.authorize(token);
    }

}

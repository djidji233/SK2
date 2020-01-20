package rs.sk.project.usersub_service.service;

import rs.sk.project.usersub_service.domain.dto.LoginRequest;
import rs.sk.project.usersub_service.domain.dto.LoginResponse;
import rs.sk.project.usersub_service.domain.dto.UserRequest;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(UserRequest userRequest);

    String authorize(String token);

}

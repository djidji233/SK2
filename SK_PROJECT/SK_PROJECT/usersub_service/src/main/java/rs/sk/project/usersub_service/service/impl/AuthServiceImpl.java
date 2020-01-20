package rs.sk.project.usersub_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rs.sk.project.usersub_service.domain.User;
import rs.sk.project.usersub_service.domain.dto.LoginRequest;
import rs.sk.project.usersub_service.domain.dto.LoginResponse;
import rs.sk.project.usersub_service.domain.dto.UserRequest;
import rs.sk.project.usersub_service.domain.dto.UserResponse;
import rs.sk.project.usersub_service.service.AuthService;
import rs.sk.project.usersub_service.service.TokenProvider;
import rs.sk.project.usersub_service.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        User user = userService.findByEmail(loginRequest.getEmail());
        if(!user.getPassword().equals(loginRequest.getPassword())) throw new RuntimeException();

        String token = tokenProvider.encrypt(loginRequest.getEmail());
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);

        return new LoginResponse(token, userResponse);
    }

    @Override
    public LoginResponse register(UserRequest userRequest) {
        UserResponse userResponse = userService.save(userRequest);
        String token = tokenProvider.encrypt(userRequest.getEmail());
        return new LoginResponse(token, userResponse);
    }

    @Override
    public String authorize(String token) {
        return tokenProvider.decrypt(token);
    }

}

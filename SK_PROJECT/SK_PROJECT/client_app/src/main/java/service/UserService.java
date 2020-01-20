package service;

import domain.LoginRequest;
import domain.LoginResponse;
import domain.RegisterRequest;
import domain.User;

public interface UserService {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(RegisterRequest registerRequest);

    User subscribe(Long cityId);

    User unsubscribe(Long cityId);

    void setAuthorization(String token);

}

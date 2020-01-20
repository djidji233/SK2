package service.impl;

import domain.LoginRequest;
import domain.LoginResponse;
import domain.RegisterRequest;
import domain.User;
import service.UserService;

public class UserServiceImpl extends AbstractHttpService implements UserService {

    public UserServiceImpl() {
        super("http://localhost:8080");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return post("auth/login", loginRequest, LoginResponse.class);
    }

    @Override
    public LoginResponse register(RegisterRequest registerRequest) {
        return post("auth/register", registerRequest, LoginResponse.class);
    }

    @Override
    public User subscribe(Long cityId) {
        return post(String.format("subscription/%s", cityId.toString()), null, User.class);
    }

    @Override
    public User unsubscribe(Long cityId) {
        return delete(String.format("subscription/%s", cityId.toString()), User.class);
    }

    @Override
    public void setAuthorization(String token) {
        super.token = token;
    }
}

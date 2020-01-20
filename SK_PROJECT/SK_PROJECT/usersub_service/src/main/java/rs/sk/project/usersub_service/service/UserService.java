package rs.sk.project.usersub_service.service;

import rs.sk.project.usersub_service.domain.User;
import rs.sk.project.usersub_service.domain.dto.UserRequest;
import rs.sk.project.usersub_service.domain.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findMyInfo(String email);

    User findByEmail(String email);

    UserResponse save(UserRequest userRequest);

    UserResponse update(User user);

}

package rs.sk.project.usersub_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rs.sk.project.usersub_service.dao.UserDao;
import rs.sk.project.usersub_service.domain.User;
import rs.sk.project.usersub_service.domain.dto.UserRequest;
import rs.sk.project.usersub_service.domain.dto.UserResponse;
import rs.sk.project.usersub_service.service.UserService;
import rs.sk.project.usersub_service.util.Exceptions;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final ModelMapper modelMapper;

    @Override
    public List<UserResponse> findAll() {
        return userDao.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponse findMyInfo(String email) {
        return modelMapper.map(findByEmail(email), UserResponse.class);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new Exceptions.ResourceNotFoundException("User not found!"));
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        if(userDao.existsByEmail(userRequest.getEmail()))
            throw new Exceptions.IllegalStatException("Email address already in use!");
        userDao.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse update(User user) {
        userDao.save(user);
        return modelMapper.map(user, UserResponse.class);
    }
}

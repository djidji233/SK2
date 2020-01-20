package rs.sk.project.usersub_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LoginResponse {

    private String token;

    private UserResponse user;

}

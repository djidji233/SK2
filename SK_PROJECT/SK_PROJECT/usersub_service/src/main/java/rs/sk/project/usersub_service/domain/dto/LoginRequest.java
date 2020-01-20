package rs.sk.project.usersub_service.domain.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;

}

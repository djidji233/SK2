package rs.sk.project.usersub_service.domain.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String email;

    private String password;

    private String fullName;

}

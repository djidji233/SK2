package domain;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;

    private User user;

}

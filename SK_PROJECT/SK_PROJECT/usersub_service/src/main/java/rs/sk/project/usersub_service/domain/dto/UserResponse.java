package rs.sk.project.usersub_service.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String id;

    private String fullName;

    private String email;

    private List<String> subscriptions;

}

package rs.sk.project.usersub_service.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    private String id;

    private String email;

    private String password;

    private String fullName;

    private List<String> subscriptions = new ArrayList<>();

}

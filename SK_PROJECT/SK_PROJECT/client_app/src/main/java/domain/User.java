package domain;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private String id;

    private String fullName;

    private String email;

    private List<Long> subscriptions;

}

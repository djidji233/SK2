package rs.sk.project.usersub_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data @AllArgsConstructor
public class MessageRequest {

    private String email;

    private Map<String, Double> data;

}

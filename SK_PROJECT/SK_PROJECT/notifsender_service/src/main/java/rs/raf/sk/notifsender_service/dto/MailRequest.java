package rs.raf.sk.notifsender_service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class MailRequest {

    private String email;

    private Map<String, Double> data;

}

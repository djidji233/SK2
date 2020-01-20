package rs.raf.sk.notifsender_service.service;

import java.util.Map;

public interface TemplateService {

    String fillNotificationTemplate (Map<String, Double> data);

}

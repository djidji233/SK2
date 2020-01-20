package rs.raf.sk.notifsender_service.service.impl;

import org.springframework.stereotype.Service;
import rs.raf.sk.notifsender_service.service.TemplateService;

import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Override
    public String fillNotificationTemplate(Map<String, Double> data) {
        StringBuilder builder = new StringBuilder(1000);
        data.forEach((city, temp) -> builder.append("<h3>").append(city).append(" - ").append(temp).append("</h3><br>"));
        return builder.toString();
    }

}

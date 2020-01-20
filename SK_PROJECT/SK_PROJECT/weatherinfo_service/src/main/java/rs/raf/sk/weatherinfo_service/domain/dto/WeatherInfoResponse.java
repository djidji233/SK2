package rs.raf.sk.weatherinfo_service.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherInfoResponse {

    private Long id;

    private String city;

    private Double temp;

    private LocalDateTime dateTime;

}

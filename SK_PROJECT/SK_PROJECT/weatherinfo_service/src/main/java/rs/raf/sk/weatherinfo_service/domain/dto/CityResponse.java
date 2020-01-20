package rs.raf.sk.weatherinfo_service.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityResponse {

    private Long id;

    private String name;

    private List<WeatherInfoResponse> measurements;

}

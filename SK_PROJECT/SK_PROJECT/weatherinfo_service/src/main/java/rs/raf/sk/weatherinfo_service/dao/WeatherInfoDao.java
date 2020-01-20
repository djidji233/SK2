package rs.raf.sk.weatherinfo_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.sk.weatherinfo_service.domain.City;
import rs.raf.sk.weatherinfo_service.domain.WeatherInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherInfoDao extends JpaRepository<WeatherInfo, Long> {

    List<WeatherInfo> findByCityAndDateTimeBetween (City city, LocalDateTime from, LocalDateTime to);

}

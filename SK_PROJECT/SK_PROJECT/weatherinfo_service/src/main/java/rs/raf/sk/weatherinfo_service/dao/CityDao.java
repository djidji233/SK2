package rs.raf.sk.weatherinfo_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.sk.weatherinfo_service.domain.City;

public interface CityDao extends JpaRepository<City, Long> {



}

package rs.raf.sk.weatherinfo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WeatherinfoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherinfoServiceApplication.class, args);
    }

}

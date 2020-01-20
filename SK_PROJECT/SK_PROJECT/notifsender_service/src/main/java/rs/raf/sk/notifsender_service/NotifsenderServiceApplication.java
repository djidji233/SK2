package rs.raf.sk.notifsender_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class NotifsenderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifsenderServiceApplication.class, args);
    }

}

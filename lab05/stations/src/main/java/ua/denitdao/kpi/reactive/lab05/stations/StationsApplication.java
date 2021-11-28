package ua.denitdao.kpi.reactive.lab05.stations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class StationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StationsApplication.class, args);
    }
}
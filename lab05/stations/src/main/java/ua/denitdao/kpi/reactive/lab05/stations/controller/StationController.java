package ua.denitdao.kpi.reactive.lab05.stations.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.denitdao.kpi.reactive.lab05.stations.domain.Station;
import ua.denitdao.kpi.reactive.lab05.stations.service.StationService;

@RestController
@Slf4j
public class StationController {

    @Autowired
    private StationService station;

    @GetMapping("/stations")
    public Flux<Station> getAllStations() {
        return station.findAll();
    }

    @GetMapping("/station")
    public Mono<Station> getStationById(@RequestParam Integer stationId) {
        return station.findById(stationId);
    }
}

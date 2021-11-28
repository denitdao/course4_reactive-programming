package ua.denitdao.kpi.reactive.lab05.stations.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.denitdao.kpi.reactive.lab05.stations.domain.Station;

public interface StationService {

    Flux<Station> findAll();

    Mono<Station> findById(Integer stationId);
}

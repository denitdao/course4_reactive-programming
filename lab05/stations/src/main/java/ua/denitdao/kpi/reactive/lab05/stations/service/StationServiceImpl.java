package ua.denitdao.kpi.reactive.lab05.stations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.denitdao.kpi.reactive.lab05.stations.domain.Station;
import ua.denitdao.kpi.reactive.lab05.stations.repository.StationRepository;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Flux<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Mono<Station> findById(Integer stationId) {
        return stationRepository.findById(stationId);
    }
}

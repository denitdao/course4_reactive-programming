package ua.denitdao.kpi.reactive.lab05.meteo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;
import ua.denitdao.kpi.reactive.lab05.meteo.dto.Record;
import ua.denitdao.kpi.reactive.lab05.meteo.dto.Station;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InternalServiceImpl implements InternalService {

    @Override
    public Tuple2<Station, List<Record>> collectFromRestSources(Integer id) {
        WebClient records = WebClient.create("http://localhost:8081");
        WebClient stations = WebClient.create("http://localhost:8082");

        Mono<Station> stationMono = stations.get()
                                            .uri("/station?stationId={id}", id)
                                            .retrieve()
                                            .bodyToMono(Station.class)
                                            .switchIfEmpty(Mono.error(new NoSuchElementException("Station doesn't exist with id=" + id)));

        Flux<Record> recordFlux = records.get()
                                         .uri("/records?stationId={id}", id)
                                         .retrieve()
                                         .bodyToFlux(Record.class);

        return Tuples.of(stationMono.block(), recordFlux.collectList().block());
    }
}

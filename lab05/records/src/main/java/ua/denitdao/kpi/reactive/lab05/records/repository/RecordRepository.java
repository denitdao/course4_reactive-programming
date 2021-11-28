package ua.denitdao.kpi.reactive.lab05.records.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ua.denitdao.kpi.reactive.lab05.records.domain.Record;

@Repository
public interface RecordRepository extends R2dbcRepository<Record, Integer> {

    Flux<Record> findAllByStationId(Integer stationId);
}

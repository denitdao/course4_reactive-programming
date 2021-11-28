package ua.denitdao.kpi.reactive.lab05.records.service;

import reactor.core.publisher.Flux;
import ua.denitdao.kpi.reactive.lab05.records.domain.Record;

public interface RecordService {

    Flux<Record> findAllRecordsByStation(Integer stationId);
}

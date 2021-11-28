package ua.denitdao.kpi.reactive.lab05.records.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ua.denitdao.kpi.reactive.lab05.records.domain.Record;
import ua.denitdao.kpi.reactive.lab05.records.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Flux<Record> findAllRecordsByStation(Integer stationId) {
        return recordRepository.findAllByStationId(stationId);
    }
}

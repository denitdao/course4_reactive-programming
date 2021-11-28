package ua.denitdao.kpi.reactive.lab05.records.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.denitdao.kpi.reactive.lab05.records.domain.Record;
import ua.denitdao.kpi.reactive.lab05.records.service.RecordService;

@RestController
@Slf4j
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/records")
    public Flux<Record> getAllRecordsByStation(@RequestParam Integer stationId) {
        return recordService.findAllRecordsByStation(stationId);
    }
}

package ua.denitdao.kpi.reactive.lab05.meteo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.denitdao.kpi.reactive.lab05.meteo.dto.Station;
import ua.denitdao.kpi.reactive.lab05.meteo.service.InternalService;
import ua.denitdao.kpi.reactive.lab05.meteo.util.Tuple2ToStationTransformer;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/meteo")
public class MeteoController {

    @Autowired
    private InternalService internalService;

    @GetMapping
    public List<Station> getStationInfo(@RequestBody List<Integer> stationIds) {

        return stationIds.stream()
                     .map(id -> internalService.collectFromRestSources(id))
                     .map(Tuple2ToStationTransformer::populateStationFromTuple2)
                     .collect(Collectors.toList());
    }
}

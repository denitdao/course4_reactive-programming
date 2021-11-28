package ua.denitdao.kpi.reactive.lab05.meteo.service;

import reactor.util.function.Tuple2;
import ua.denitdao.kpi.reactive.lab05.meteo.dto.Record;
import ua.denitdao.kpi.reactive.lab05.meteo.dto.Station;

import java.util.List;

public interface InternalService {

    Tuple2<Station, List<Record>> collectFromRestSources(Integer id);
}

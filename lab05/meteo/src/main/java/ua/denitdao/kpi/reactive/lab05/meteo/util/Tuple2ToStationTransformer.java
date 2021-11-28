package ua.denitdao.kpi.reactive.lab05.meteo.util;

import reactor.util.function.Tuple2;
import ua.denitdao.kpi.reactive.lab05.meteo.dto.Record;
import ua.denitdao.kpi.reactive.lab05.meteo.dto.Station;

import java.util.List;

public class Tuple2ToStationTransformer {

    public static Station populateStationFromTuple2(Tuple2<Station, List<Record>> tuple2) {
        Station station = tuple2.getT1();
        station.setRecords(tuple2.getT2());
        return station;
    }

    private Tuple2ToStationTransformer() {
    }
}

package ua.denitdao.kpi.reactive.lab05.stations.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ua.denitdao.kpi.reactive.lab05.stations.domain.Station;

@Repository
public interface StationRepository extends R2dbcRepository<Station, Integer> {
    
}

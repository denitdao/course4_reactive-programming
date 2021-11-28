package ua.denitdao.kpi.reactive.lab05.meteo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private Integer stationId;
    private String description;
    private LocalDateTime createdAt;
}

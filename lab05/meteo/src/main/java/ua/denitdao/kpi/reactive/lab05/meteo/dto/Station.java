package ua.denitdao.kpi.reactive.lab05.meteo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    private String name;
    private String location;
    private List<Record> records;
}

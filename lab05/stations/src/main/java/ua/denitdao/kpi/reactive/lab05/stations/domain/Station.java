package ua.denitdao.kpi.reactive.lab05.stations.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    private Integer id;
    private String name;
    private String location;
}

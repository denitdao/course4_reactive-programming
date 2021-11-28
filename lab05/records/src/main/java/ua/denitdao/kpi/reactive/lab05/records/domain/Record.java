package ua.denitdao.kpi.reactive.lab05.records.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    private Integer id;
    private Integer stationId;
    private String description;
    private LocalDateTime createdAt;
}

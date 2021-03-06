package ua.denitdao.kpi.reactive.streamtasks.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Student {
    private String name;
    private double rating;
    private List<Exam> exams = new ArrayList<>();
}
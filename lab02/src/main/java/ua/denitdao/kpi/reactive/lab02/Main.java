package ua.denitdao.kpi.reactive.lab02;

import lombok.extern.slf4j.Slf4j;
import ua.denitdao.kpi.reactive.streamtasks.entities.Exam;
import ua.denitdao.kpi.reactive.streamtasks.repositories.StudentRepository;
import ua.denitdao.kpi.reactive.streamtasks.services.StudentService;

import java.util.Arrays;
import java.util.HashSet;

@Slf4j
public class Main {

    private static StudentService studentService = new StudentService(new StudentRepository());

    public static void main(String[] args) {
        log.info("Найти студентов без экзаменов \n{}",
                studentService.findWithoutExams());
        log.info("Найти студентов, которые сдавали 2 экзамена \n{}",
                studentService.findWithNumberOfExams(2));
        log.info("Найти студентов, которые сдавали математику и английский с результатом каждого не меньше 180 \n{}",
                studentService.findPassedExamsWithMoreThan(new HashSet<>(Arrays.asList(Exam.Type.ENGLISH, Exam.Type.MATH)), 180.));
        log.info("Найти студентов, которые имеют результат по математике выше среднего и сдали английский \n{}",
                studentService.findPassedBetterThanAvgAndPassed(Exam.Type.MATH, Exam.Type.ENGLISH));
        log.info("Найти студента с максимальным баллом по английскому \n{}",
                studentService.findWithMaxExam(Exam.Type.ENGLISH));
    }

}

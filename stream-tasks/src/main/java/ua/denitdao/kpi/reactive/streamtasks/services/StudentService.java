package ua.denitdao.kpi.reactive.streamtasks.services;

import org.apache.commons.collections4.CollectionUtils;
import ua.denitdao.kpi.reactive.streamtasks.entities.Exam;
import ua.denitdao.kpi.reactive.streamtasks.entities.Student;
import ua.denitdao.kpi.reactive.streamtasks.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import static ua.denitdao.kpi.reactive.streamtasks.entities.Exam.Type;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findWithoutExams() {
        return studentRepository.findAll().stream()
                .filter(s -> CollectionUtils.isEmpty(s.getExams()))
                .collect(Collectors.toList());
    }

    public List<Student> findWithNumberOfExams(int numOfExams) {
        return studentRepository.findAll().stream()
                .filter(s -> CollectionUtils.isNotEmpty(s.getExams()) && s.getExams().size() == numOfExams)
                .collect(Collectors.toList());
    }

    public List<Student> findPassedExamsWithMoreThan(Set<Type> examSet, double passRate) {
        return studentRepository.findAll().stream()
                .filter(s -> CollectionUtils.isNotEmpty(s.getExams()))
                .filter(s -> s.getExams().stream()
                        .filter(exam -> examSet.contains(exam.getType()) && exam.getScore() >= passRate)
                        .collect(Collectors.toSet()).size() == examSet.size())
                .collect(Collectors.toList());
    }

    public List<Student> findPassedBetterThanAvgAndPassed(Type betterThanAvg, Type passed) {
        List<Student> students = studentRepository.findAll();

        OptionalDouble average = students.stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == betterThanAvg)
                .mapToDouble(Exam::getScore)
                .average();

        if (average.isEmpty())
            return Collections.emptyList();

        return studentRepository.findAll().stream()
                .filter(s -> CollectionUtils.isNotEmpty(s.getExams()))
                .filter(s -> s.getExams().stream()
                        .anyMatch(e -> (e.getType() == passed)))
                .filter(s -> s.getExams().stream()
                        .anyMatch(e -> (e.getType() == betterThanAvg) && (e.getScore() > average.getAsDouble())))
                .collect(Collectors.toList());
    }

    public Optional<Student> findWithMaxExam(Type type) {
        List<Student> students = studentRepository.findAll();
        OptionalDouble maxExam = students.stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == type)
                .mapToDouble(Exam::getScore)
                .max();

        if (maxExam.isEmpty())
            return Optional.empty();

        Exam exam = Exam.of(type, maxExam.getAsDouble());
        return students.stream()
                .filter(student -> student.getExams().contains(exam))
                .findFirst();
    }
}

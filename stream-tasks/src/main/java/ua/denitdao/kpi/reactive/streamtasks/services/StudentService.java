package ua.denitdao.kpi.reactive.streamtasks.services;

import ua.denitdao.kpi.reactive.streamtasks.entities.Exam;
import ua.denitdao.kpi.reactive.streamtasks.entities.Student;
import ua.denitdao.kpi.reactive.streamtasks.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static ua.denitdao.kpi.reactive.streamtasks.entities.Exam.Type;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findWithMaxExam(Type type) {
        List<Student> students = studentRepository.findAll();
        OptionalDouble maxExam = students.stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == type)
                .mapToDouble(Exam::getScore)
                .max();

        if (!maxExam.isPresent())
            return Optional.empty();

        Exam exam = Exam.of(type, maxExam.getAsDouble());
        return students.stream()
                .filter(student -> student.getExams().contains(exam))
                .findFirst();
    }

    public List<Student> findWithEnoughExam(Type examType, double passRate) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .filter(exam -> exam.getType() == examType &&
                                exam.getScore() >= passRate)
                        .findAny().isPresent())
                .collect(Collectors.toList());
    }
}

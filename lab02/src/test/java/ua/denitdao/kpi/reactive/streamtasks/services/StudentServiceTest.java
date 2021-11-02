package ua.denitdao.kpi.reactive.streamtasks.services;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import ua.denitdao.kpi.reactive.streamtasks.entities.Exam;
import ua.denitdao.kpi.reactive.streamtasks.entities.Student;
import ua.denitdao.kpi.reactive.streamtasks.repositories.StudentRepository;

import static ua.denitdao.kpi.reactive.streamtasks.entities.Exam.Type;

class StudentServiceTest {

    private Student firstStudent = Student.builder()
            .name("1")
            .rating(10)
            .exams(List.of(Exam.of(Type.MATH, 181)))
            .build();
    private Student secondStudent = Student.builder()
            .name("2")
            .rating(11)
            .exams(Arrays.asList(
                    Exam.of(Exam.Type.ENGLISH, 182),
                    Exam.of(Exam.Type.MATH, 190)))
            .build();
    private Student thirdStudent = Student.builder()
            .name("3")
            .rating(11)
            .exams(Arrays.asList(
                    Exam.of(Exam.Type.ENGLISH, 183),
                    Exam.of(Exam.Type.MATH, 190)))
            .build();
    private Student fourthStudent = Student.builder()
            .name("4")
            .rating(11)
            .exams(List.of())
            .build();
    private Student fifthStudent = Student.builder()
            .name("5")
            .rating(12)
            .exams(Arrays.asList(
                    Exam.of(Exam.Type.ENGLISH, 180),
                    Exam.of(Exam.Type.MATH, 130)))
            .build();

    private StudentRepository createStudentRepositoryWithAllStudents() {
        StudentRepository studentRepository = mock(StudentRepository.class);
        List<Student> allStudents = Arrays.asList(firstStudent, secondStudent,
                thirdStudent, fourthStudent, fifthStudent);
        when(studentRepository.findAll()).thenReturn(allStudents);
        return studentRepository;
    }

    @Test
    void should_find_students_without_exams() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        List<Student> actual = studentService.findWithoutExams();
        assertEquals(List.of(fourthStudent), actual);
    }

    @Test
    void should_find_students_with_num_of_exams() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        int numOfExams = 2;

        List<Student> actual = studentService.findWithNumberOfExams(numOfExams);
        assertEquals(List.of(secondStudent, thirdStudent, fifthStudent), actual);
    }

    @Test
    void should_find_passed_exams_with_score_more_than() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);

        List<Student> actual = studentService.findPassedExamsWithMoreThan(new HashSet<>(Arrays.asList(Exam.Type.ENGLISH, Exam.Type.MATH)), 182);

        assertEquals(List.of(secondStudent, thirdStudent), actual);
    }

    @Test
    void should_find_passed_better_than_average_and_passed() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);

        List<Student> actual = studentService.findPassedBetterThanAvgAndPassed(Type.MATH, Type.ENGLISH);

        assertEquals(List.of(secondStudent, thirdStudent), actual);
    }

    @Test
    void should_find_student_with_max_english() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        Optional<Student> studentOpt = studentService.findWithMaxExam(Type.ENGLISH);
        assertEquals(Optional.of(thirdStudent), studentOpt);
    }
}
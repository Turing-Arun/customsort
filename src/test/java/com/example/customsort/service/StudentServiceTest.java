package com.example.customsort.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import com.example.customsort.dto.Student;


@SpringBootTest
public class StudentServiceTest {

    private final StudentService studentService = new StudentService();
    List<Student> students;

    @BeforeEach
    void setup() {
        students = Arrays.asList(
            new Student("Alice", 20, 3.5, 160),
            new Student("Bob", 22, 3.2, 170),
            new Student("Charlie", 21, 3.4, 165)
        );
    }

    @AfterEach
    void tearDown() {
        students = null;
    }

    @Test
    void testSortByAge() {

        List<Student> sortedStudents = studentService.sortByAge(students);

        assertEquals(20, sortedStudents.get(0).getAge());
        assertEquals(21, sortedStudents.get(1).getAge());
        assertEquals(22, sortedStudents.get(2).getAge());
    }

    @Test
    void testSortByCgpa() {

        List<Student> sortedStudents = studentService.sortByCgpa(students);

        assertEquals(3.2, sortedStudents.get(0).getCgpa());
        assertEquals(3.4, sortedStudents.get(1).getCgpa());
        assertEquals(3.5, sortedStudents.get(2).getCgpa());
    }

    @Test
    void testSortByHeight() {
        List<Student> sortedStudents = studentService.sortByHeight(students);

        assertEquals(160, sortedStudents.get(0).getHeight());
        assertEquals(165, sortedStudents.get(1).getHeight());
        assertEquals(170, sortedStudents.get(2).getHeight());
    }

    @Test
    void testSortByName() {
        List<Student> sortedStudents = studentService.sortByName(students);

        assertEquals("Alice", sortedStudents.get(0).getName());
        assertEquals("Bob", sortedStudents.get(1).getName());
        assertEquals("Charlie", sortedStudents.get(2).getName());
    }
}

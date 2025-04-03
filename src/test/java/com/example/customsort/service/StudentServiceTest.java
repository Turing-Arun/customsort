package com.example.customsort.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.customsort.dto.Student;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {

  private final StudentService studentService = new StudentService();
  List<Student> students;

  @BeforeEach
  void setup() {
    students =
        Arrays.asList(
            new Student("Alice", 20, 3.5, 160),
            new Student("Bob", 22, 3.2, 170),
            new Student("Charlie", 21, 3.4, 165));
  }

  @AfterEach
  void tearDown() {
    students = null;
  }

  // test sort by age in ascending order
  @Test
  void testSortByAge() {

    List<Student> sortedStudents = studentService.sortStudents(students, "age");

    assertEquals(20, sortedStudents.get(0).getAge());
    assertEquals(21, sortedStudents.get(1).getAge());
    assertEquals(22, sortedStudents.get(2).getAge());
  }

  // test sort by cgpa in ascending order
  @Test
  void testSortByCgpa() {

    List<Student> sortedStudents = studentService.sortStudents(students, "cgpa");

    assertEquals(3.2, sortedStudents.get(0).getCgpa());
    assertEquals(3.4, sortedStudents.get(1).getCgpa());
    assertEquals(3.5, sortedStudents.get(2).getCgpa());
  }

  // test sort by height in ascending order
  @Test
  void testSortByHeight() {
    List<Student> sortedStudents = studentService.sortStudents(students, "height");

    assertEquals(160, sortedStudents.get(0).getHeight());
    assertEquals(165, sortedStudents.get(1).getHeight());
    assertEquals(170, sortedStudents.get(2).getHeight());
  }

  // test sort by name in alphabetical order
  @Test
  void testSortByName() {
    List<Student> sortedStudents = studentService.sortStudents(students, "name");

    assertEquals("Alice", sortedStudents.get(0).getName());
    assertEquals("Bob", sortedStudents.get(1).getName());
    assertEquals("Charlie", sortedStudents.get(2).getName());
  }

  // test sort by name in reverse alphabetical order
  @Test
  void testSortByInvalidSortByParam() {
    assertThrows(
        IllegalArgumentException.class, () -> studentService.sortStudents(students, "invalid"));
  }
}

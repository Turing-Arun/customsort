package com.example.customsort.service;

import com.example.customsort.dto.Student;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service class for managing and sorting students.
 *
 * <p>This service provides functionality to sort a list of students based on various attributes
 * such as CGPA, name, height, and age. The sorting is performed using comparators stored in a map,
 * which can be extended to support additional attributes if needed.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * List<Student> students = // initialize list of students
 * StudentService studentService = new StudentService();
 * List<Student> sortedByCgpa = studentService.sortStudents(students, "cgpa");
 * List<Student> sortedByName = studentService.sortStudents(students, "name");
 * }</pre>
 *
 * <h2>Role</h2>
 *
 * <p>The role of this service class is to provide a centralized and reusable way to sort student
 * data based on different attributes. It encapsulates the sorting logic and ensures that the
 * sorting criteria are easily configurable and extendable.
 *
 * @see com.example.customsort.dto.Student
 * @see java.util.Comparator
 * @see java.util.HashMap
 */
@Service
public class StudentService {
  /**
   * A map that holds comparators for sorting students based on different attributes. The key is the
   * attribute name, and the value is the corresponding comparator.
   */
  private static final HashMap<String, Comparator<Student>> comparatorMap = new HashMap<>();

  static {
    comparatorMap.put("cgpa", Comparator.comparing(Student::getCgpa));
    comparatorMap.put("name", Comparator.comparing(Student::getName));
    comparatorMap.put("height", Comparator.comparing(Student::getHeight));
    comparatorMap.put("age", Comparator.comparing(Student::getAge));
  }

  /**
   * Sorts a list of students based on the specified attribute.
   *
   * @param students the list of students to be sorted
   * @param sortBy the attribute to sort the students by
   * @return the sorted list of students
   * @throws IllegalArgumentException if the specified sortBy attribute is not supported
   */
  public List<Student> sortStudents(List<Student> students, String sortBy) {
    Comparator<Student> comparator =
        comparatorMap.computeIfAbsent(
            sortBy,
            key -> {
              throw new IllegalArgumentException("Unsupported sortBy:" + key);
            });
    students.sort(comparator);
    return students;
  }
}

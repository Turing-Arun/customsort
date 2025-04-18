package com.example.customsort.controller;

import com.example.customsort.dto.Student;
import com.example.customsort.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * This controller class handles HTTP requests related to student operations. It provides an
 * endpoint to sort a list of students based on various parameters.
 *
 * <p>Supported sort parameters are: - "name": Sorts students by their names in alphabetical order.
 * - "age": Sorts students by their age in ascending order. - "height": Sorts students by their
 * height in ascending order. - "cgpa": Sorts students by their CGPA in descending order.
 *
 * <p>The controller also handles exceptions for invalid sort parameters.
 *
 * <p>Dependencies: - StudentService: A service class that provides sorting functionalities.
 *
 * <p>Endpoints: - POST /students/sort: Sorts the list of students based on the given sort
 * parameter.
 *
 * <p>Exception Handling: - IllegalArgumentException: Returns a bad request status code with an
 * error message if the sort parameter is invalid.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

  /** Logger instance for logging */
  private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

  /** This is a service class object */
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * This method sorts the list of students based on the given sort parameter. Supported sort
   * parameters are: - "name": Sorts students by their names in alphabetical order. - "age": Sorts
   * students by their age in ascending order. - "height": Sorts students by their height in
   * ascending order. - "cgpa": Sorts students by their CGPA in descending order.
   *
   * @param students list of students to be sorted
   * @param sortBy the parameter to sort the students by (name, age, height, cgpa)
   * @return sorted list of students
   * @throws IllegalArgumentException if the sort parameter is invalid and returns a bad request
   *     status code
   */
  @PostMapping("/sort")
  public List<Student> sortStudents(
      @Valid @RequestBody List<Student> students, @Valid @RequestParam String sortBy)
      throws IllegalArgumentException {
    logger.info("Received request to sort students with sortBy parameter: {}", sortBy);
    try {
      List<Student> sortedStudents = studentService.sortStudents(students, sortBy);
      logger.info("Successfully sorted students by {}", sortBy);
      return sortedStudents;
    } catch (IllegalArgumentException e) {
      logger.error("Error occurred while sorting students: {}", e.getMessage());
      throw e;
    }
  }
}

package com.example.customsort.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.customsort.dto.Student;

/**
 * Service class for sorting a list of Student objects based on different attributes.
 * This class provides methods to sort students by name, age, height, and CGPA.
 * 
 * <p>Each sorting method uses Java's Comparator to sort the list in ascending order
 * based on the specified attribute.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * List<Student> sortedByName = studentService.sortByName(students);
 * List<Student> sortedByAge = studentService.sortByAge(students);
 * List<Student> sortedByHeight = studentService.sortByHeight(students);
 * List<Student> sortedByCgpa = studentService.sortByCgpa(students);
 * }
 * </pre>
 * 
 * <p>This class is annotated with @Service, indicating that it's a Spring service component.</p>
 */
@Service
public class StudentService {

    /**
     * Sorts a list of students by their names in ascending order.
     * 
     * @param students the list of students to be sorted
     * @return the sorted list of students
     */
    public List<Student> sortByName(List<Student> students) {
        students.sort(Comparator.comparing(Student::getName));
        return students;
    }

    /**
     * Sorts a list of students by their ages in ascending order.
     * 
     * @param students the list of students to be sorted
     * @return the sorted list of students
     */
    public List<Student> sortByAge(List<Student> students) {
        students.sort(Comparator.comparing(Student::getAge));
        return students;
    }
    
    /**
     * Sorts a list of students by their heights in ascending order.
     * 
     * @param students the list of students to be sorted
     * @return the sorted list of students
     */
    public List<Student> sortByHeight(List<Student> students) {
        students.sort(Comparator.comparing(Student::getHeight));
        return students;
    }
    
    /**
     * Sorts a list of students by their CGPA (Cumulative Grade Point Average) in ascending order.
     * 
     * @param students the list of students to be sorted
     * @return the sorted list of students
     */
    public List<Student> sortByCgpa(List<Student> students) {
        students.sort(Comparator.comparing(Student::getCgpa));
        return students;
    }        
}

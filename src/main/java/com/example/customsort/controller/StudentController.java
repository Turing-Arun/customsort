package com.example.customsort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.customsort.dto.Student;
import com.example.customsort.service.StudentService;

import java.util.List;

/**
 * This is a rest contoller for student endpoints
 */

@RestController
@RequestMapping("/students")
public class StudentController {

    /**
     * This is a service class object
     */
    @Autowired
    private StudentService studentService;

    @PostMapping("/sort")
    public List<Student> sortStudents(@RequestBody List<Student> students, @RequestParam String sortBy) throws IllegalArgumentException
    {
        switch (sortBy)
        {
            case "name":
                return studentService.sortByName(students);
            case "age":
                return studentService.sortByAge(students);
            case "height":
                return studentService.sortByHeight(students);
            case "cgpa":
                return studentService.sortByCgpa(students);
            default:
                throw new IllegalArgumentException("Invalid sort parameter: " + sortBy);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }    
}
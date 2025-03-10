package com.example.customsort.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.customsort.dto.Student;

@Service
public class StudentService {

    public List<Student> sortByName(List<Student> students) {
        students.sort(Comparator.comparing(Student::getName));
        return students;
    }

    public List<Student> sortByAge(List<Student> students) {
        students.sort(Comparator.comparing(Student::getAge));
        return students;
    }
    
    public List<Student> sortByHeight(List<Student> students) {
        students.sort(Comparator.comparing(Student::getHeight));
        return students;
    }
    
    public List<Student> sortByCgpa(List<Student> students) {
        students.sort(Comparator.comparing(Student::getCgpa));
        return students;
    }        
}

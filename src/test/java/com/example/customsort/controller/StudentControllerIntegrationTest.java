package com.example.customsort.controller;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.customsort.dto.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private List<Student> students;

    @BeforeEach
    public void setup() {
        students = Arrays.asList(
                new Student("Alice", 20, 3.5, 160),
                new Student("Bob", 22, 3.2, 170),
                new Student("Charlie", 21, 3.4, 165)
        );
    }

    @Test
    @DisplayName("Integration Test sorting by name")
    @Order(1)
    public void testSortByName() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<List<Student>> request = new HttpEntity<>(students, headers);

        ResponseEntity<Student[]> response = restTemplate.exchange(
                "/students/sort?sortBy=name",
                HttpMethod.POST,
                request,
                Student[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(OK);
        Student[] sortedStudents = response.getBody();
        assertThat(sortedStudents).isNotNull();
        assertThat(sortedStudents[0].getName()).isEqualTo("Alice");
        assertThat(sortedStudents[1].getName()).isEqualTo("Bob");
        assertThat(sortedStudents[2].getName()).isEqualTo("Charlie");
    }

    @Test
    @DisplayName("Integration Test sorting by age")
    @Order(2)
    public void testSortByAge() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<List<Student>> request = new HttpEntity<>(students, headers);

        ResponseEntity<Student[]> response = restTemplate.exchange(
                "/students/sort?sortBy=age",
                HttpMethod.POST,
                request,
                Student[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(OK);
        Student[] sortedStudents = response.getBody();
        assertThat(sortedStudents).isNotNull();
        assertThat(sortedStudents[0].getAge()).isEqualTo(20);
        assertThat(sortedStudents[1].getAge()).isEqualTo(21);
        assertThat(sortedStudents[2].getAge()).isEqualTo(22);
    }

    @Test
    @DisplayName("Integration Test sorting by height")
    @Order(3)
    public void testSortByHeight() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<List<Student>> request = new HttpEntity<>(students, headers);

        ResponseEntity<Student[]> response = restTemplate.exchange(
                "/students/sort?sortBy=height",
                HttpMethod.POST,
                request,
                Student[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(OK);
        Student[] sortedStudents = response.getBody();
        assertThat(sortedStudents).isNotNull();
        assertThat(sortedStudents[0].getHeight()).isEqualTo(160);
        assertThat(sortedStudents[1].getHeight()).isEqualTo(165);
        assertThat(sortedStudents[2].getHeight()).isEqualTo(170);
    }

    @Test
    @DisplayName("Integration Test sorting by CGPA")
    @Order(4)
    public void testSortByCgpa() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<List<Student>> request = new HttpEntity<>(students, headers);

        ResponseEntity<Student[]> response = restTemplate.exchange(
                "/students/sort?sortBy=cgpa",
                HttpMethod.POST,
                request,
                Student[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(OK);
        Student[] sortedStudents = response.getBody();
        assertThat(sortedStudents).isNotNull();
        assertThat(sortedStudents[0].getCgpa()).isEqualTo(3.2);
        assertThat(sortedStudents[1].getCgpa()).isEqualTo(3.4);
        assertThat(sortedStudents[2].getCgpa()).isEqualTo(3.5);
    }

    @Test
    @DisplayName("Integration Test sorting with invalid sortBy parameter")
    @Order(5)
    public void testSortWithInvalidParameter() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<List<Student>> request = new HttpEntity<>(students, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "/students/sort?sortBy=invalid",
                HttpMethod.POST,
                request,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Unsupported sortBy:invalid");
    }
    
}

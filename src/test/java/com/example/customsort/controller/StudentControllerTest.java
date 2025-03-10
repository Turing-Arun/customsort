
package com.example.customsort.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.customsort.dto.Student;
import com.example.customsort.service.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

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
    @DisplayName("Test sorting by name")
    @Order(1)
    public void testSortByName() throws Exception {
        List<Student> sortedStudents = Arrays.asList(
                new Student("Alice", 20, 3.5, 160),
                new Student("Bob", 22, 3.2, 170),
                new Student("Charlie", 21, 3.4, 165)
        );

        Mockito.when(studentService.sortByName(students)).thenReturn(sortedStudents);

        mockMvc.perform(post("/students/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Alice\",\"age\":20,\"cgpa\":3.5,\"height\":160},{\"name\":\"Bob\",\"age\":22,\"cgpa\":3.2,\"height\":170},{\"name\":\"Charlie\",\"age\":21,\"cgpa\":3.4,\"height\":165}]")
                        .param("sortBy", "name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"))
                .andExpect(jsonPath("$[2].name").value("Charlie"));
    }

    @Test
    @DisplayName("Test sorting by age")
    @Order(2)
    public void testSortByAge() throws Exception {
        List<Student> sortedStudents = Arrays.asList(
                new Student("Alice", 20, 3.5, 160),
                new Student("Charlie", 21, 3.4, 165),
                new Student("Bob", 22, 3.2, 170)
        );

        Mockito.when(studentService.sortByAge(students)).thenReturn(sortedStudents);

        mockMvc.perform(post("/students/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Alice\",\"age\":20,\"cgpa\":3.5,\"height\":160},{\"name\":\"Bob\",\"age\":22,\"cgpa\":3.2,\"height\":170},{\"name\":\"Charlie\",\"age\":21,\"cgpa\":3.4,\"height\":165}]")
                        .param("sortBy", "age"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[1].age").value(21))
                .andExpect(jsonPath("$[2].age").value(22));
    }

    @Test
    @DisplayName("Test sorting by height")
    @Order(3)
    public void testSortByHeight() throws Exception {
        List<Student> sortedStudents = Arrays.asList(
                new Student("Alice", 20, 3.5, 160),
                new Student("Charlie", 21, 3.4, 165),
                new Student("Bob", 22, 3.2, 170)
        );

        Mockito.when(studentService.sortByHeight(students)).thenReturn(sortedStudents);

        mockMvc.perform(post("/students/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Alice\",\"age\":20,\"cgpa\":3.5,\"height\":160},{\"name\":\"Bob\",\"age\":22,\"cgpa\":3.2,\"height\":170},{\"name\":\"Charlie\",\"age\":21,\"cgpa\":3.4,\"height\":165}]")
                        .param("sortBy", "height"))
                .andExpect(jsonPath("$[0].height").value(160))
                .andExpect(jsonPath("$[1].height").value(165))
                .andExpect(jsonPath("$[2].height").value(170));
    }

    @Test
    @DisplayName("Test sorting by CGPA")
    @Order(4)
    public void testSortByCgpa() throws Exception {
        List<Student> sortedStudents = Arrays.asList(
                new Student("Bob", 22, 3.2, 170),
                new Student("Charlie", 21, 3.4, 165),
                new Student("Alice", 20, 3.5, 160)
        );

        Mockito.when(studentService.sortByCgpa(students)).thenReturn(sortedStudents);

        mockMvc.perform(post("/students/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Alice\",\"age\":20,\"cgpa\":3.5,\"height\":160},{\"name\":\"Bob\",\"age\":22,\"cgpa\":3.2,\"height\":170},{\"name\":\"Charlie\",\"age\":21,\"cgpa\":3.4,\"height\":165}]")
                        .param("sortBy", "cgpa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cgpa").value(3.2))
                .andExpect(jsonPath("$[1].cgpa").value(3.4))
                .andExpect(jsonPath("$[2].cgpa").value(3.5));
    }

    @Test
    @DisplayName("Test sorting with invalid sortBy parameter")
    @Order(5)
    public void testSortWithInvalidParameter() throws Exception {
        mockMvc.perform(post("/students/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Alice\",\"age\":20,\"cgpa\":3.5,\"height\":160},{\"name\":\"Bob\",\"age\":22,\"cgpa\":3.2,\"height\":170},{\"name\":\"Charlie\",\"age\":21,\"cgpa\":3.4,\"height\":165}]")
                        .param("sortBy", "invalid"))
                .andExpect(status().isBadRequest());
    }
}

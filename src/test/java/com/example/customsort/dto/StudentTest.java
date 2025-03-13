package com.example.customsort.dto;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testStudentConstructorAndGetters() {
        Student student = new Student("Alice", 20, 3.5, 160);

        assertEquals("Alice", student.getName());
        assertEquals(20, student.getAge());
        assertEquals(3.5, student.getCgpa());
        assertEquals(160, student.getHeight());
    }

    @Test
    public void testStudentSetters() {
        Student student = new Student();
        student.setName("Bob");
        student.setAge(22);
        student.setCgpa(3.2);
        student.setHeight(170);

        assertEquals("Bob", student.getName());
        assertEquals(22, student.getAge());
        assertEquals(3.2, student.getCgpa());
        assertEquals(170, student.getHeight());
    }

    @Test
    public void testStudentNoArgsConstructor() {
        Student student = new Student();

        assertNull(student.getName());
        assertEquals(0, student.getAge());
        assertEquals(0.0, student.getCgpa());
        assertEquals(0, student.getHeight());
    }

    @Test
    public void testStudentAllArgsConstructor() {
        Student student = new Student("Charlie", 21, 3.4, 165);

        assertEquals("Charlie", student.getName());
        assertEquals(21, student.getAge());
        assertEquals(3.4, student.getCgpa());
        assertEquals(165, student.getHeight());
    }

    @Test
    public void testStudentEqualsAndHashCode() {
        Student student1 = new Student("Alice", 20, 3.5, 160);
        Student student2 = new Student("Alice", 20, 3.5, 160);
        Student student3 = new Student("Bob", 22, 3.2, 170);

        assertEquals(student1, student2);
        assertNotEquals(student1, student3);
        assertEquals(student1.hashCode(), student2.hashCode());
        assertNotEquals(student1.hashCode(), student3.hashCode());
    }

    @Test
    public void testStudentToString() {
        Student student = new Student("Alice", 20, 3.5, 160);
        String expected = "Student(name=Alice, age=20, cgpa=3.5, height=160)";

        assertEquals(expected, student.toString());
    }

    @Test
    public void equalsVerifier() {
        EqualsVerifier.simple().forClass(Student.class).verify();
    }


}
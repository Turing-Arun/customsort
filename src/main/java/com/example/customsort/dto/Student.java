package com.example.customsort.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Student class represents a student with a name, age, CGPA, and height.
 * <p>
 * This class is used to store and retrieve information about a student.
 * </p>
 * 
 * <ul>
 * <li>{@link #name} - The name of the student.</li>
 * <li>{@link #age} - The age of the student.</li>
 * <li>{@link #cgpa} - The CGPA of the student.</li>
 * <li>{@link #height} - The height of the student in centimeters.</li>
 * </ul>
 * 
 * <p>
 * This class uses Lombok annotations to generate boilerplate code such as
 * getters, setters, constructors, and toString methods.
 * </p>
 * 
 * @see lombok.Data
 * @see lombok.AllArgsConstructor
 * @see lombok.NoArgsConstructor
 * @see lombok.Getter
 * @see lombok.Setter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    /**
     * This field represents the name of the student
     */
    private String name;
    /**
     * This field represents the age of the student
     */
    private int age;
    /**
     * This field represents the cgpa of the student
     */
    private double cgpa;
    /**
     * This field represents the height of the student in cms
     */
    private int height;
}

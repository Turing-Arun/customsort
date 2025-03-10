package com.example.customsort.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This class represents a student entity
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

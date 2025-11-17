package com.school;

/**
 * Student class extending Person
 * Represents a student in the school.
 */
public class Student extends Person {
    private int gradeLevel;

    /**
     * Constructor for Student class
     * 
     * @param name       The name of the student
     * @param gradeLevel The grade level of the student
     */
    public Student(String name, int gradeLevel) {
        super(name);
        this.gradeLevel = gradeLevel;
    }

    // Getters for encapsulation
    public int getStudentId() {
        return id;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    /**
     * Override displayDetails to show student-specific information
     */
    @Override
    public void displayDetails() {
        System.out.println("--- Student Details ---");
        System.out.println("Student ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Grade Level: " + this.gradeLevel);
    }
}

package com.school;

public class Course implements Storable {
    private static int nextCourseIdCounter = 101;
    private int courseId;
    private String courseName;

    public Course(String courseName) {
        this.courseId = nextCourseIdCounter++;
        this.courseName = courseName;
    }

    // Getters for encapsulation
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void displayDetails() {
        System.out.println("Course ID: C" + this.courseId);
        System.out.println("Course Name: " + this.courseName);
    }

    /**
     * Convert Course to CSV format for storage
     */
    @Override
    public String toDataString() {
        return courseId + "," + courseName;
    }
}

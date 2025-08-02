package com.school;

public class Course {
    String courseId;
    String courseName;

    public void setDetails(String id, String cName) {
        this.courseId = id;
        this.courseName = cName;
    }

    public void displayDetails() {
        System.out.println("Course ID: " + this.courseId + ", Name: " + this.courseName);
    }
}
package com.school;

import java.util.ArrayList;
import java.util.List;

public class Course implements Storable {
    private static int nextCourseIdCounter = 101;
    private int courseId;
    private String courseName;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String courseName, int capacity) {
        this.courseId = nextCourseIdCounter++;
        this.courseName = courseName;
        this.capacity = Math.max(0, capacity);
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters for encapsulation
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledCount() {
        return enrolledStudents.size();
    }

    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);
    }

    /**
     * Attempt to enroll a student in this course.
     * Returns true if enrollment succeeded, false otherwise (full or already
     * enrolled).
     */
    public boolean enrollStudent(Student s) {
        if (s == null)
            return false;
        // prevent duplicate enrollments
        for (Student existing : enrolledStudents) {
            if (existing.getId() == s.getId())
                return false;
        }
        if (enrolledStudents.size() >= capacity)
            return false;
        enrolledStudents.add(s);
        return true;
    }

    public void displayDetails() {
        System.out.println("Course ID: C" + this.courseId);
        System.out.println("Course Name: " + this.courseName);
        System.out.println("Capacity: " + this.capacity);
        System.out.println("Enrolled: " + getEnrolledCount());
    }

    /**
     * Convert Course to CSV format for storage (includes capacity)
     */
    @Override
    public String toDataString() {
        return courseId + "," + courseName + "," + capacity;
    }
}

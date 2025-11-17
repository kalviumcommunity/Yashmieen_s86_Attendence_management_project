package com.school;

public class AttendanceRecord implements Storable {
    private Student student;
    private Course course;
    private String status;

    /**
     * Constructor for AttendanceRecord
     * Now holds Student and Course objects instead of just their IDs,
     * enhancing the object-oriented nature of the design
     * 
     * @param student The Student object
     * @param course  The Course object
     * @param status  The attendance status ("Present" or "Absent")
     */
    public AttendanceRecord(Student student, Course course, String status) {
        this.student = student;
        this.course = course;

        // Validation
        if (status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent")) {
            this.status = status;
        } else {
            this.status = "Invalid";
            System.out.println("⚠️ Warning: Invalid status provided. Defaulted to 'Invalid'.");
        }
    }

    // Getters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    // Display method - now uses full object details for better reporting
    public void displayRecord() {
        System.out.println("Student: " + student.getName() + " (ID: " + student.getId() + ") | " +
                "Course: " + course.getCourseName() + " (ID: C" + course.getCourseId() + ") | " +
                "Status: " + status);
    }

    /**
     * Convert AttendanceRecord to CSV format for storage
     * Still uses IDs for simpler and more flexible file storage
     */
    @Override
    public String toDataString() {
        return student.getId() + "," + course.getCourseId() + "," + status;
    }
}

package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[4];
        Teacher[] teachers = new Teacher[2];
        Staff[] staffMembers = new Staff[2];
        Course[] courses = new Course[3];

        // Create students using constructor
        students[0] = new Student("Alice", 10);
        students[1] = new Student("Bob", 9);
        students[2] = new Student("Charlie", 10);
        students[3] = new Student("Diana", 9);

        // Create teachers using constructor
        teachers[0] = new Teacher("Mr. John Smith", "Mathematics");
        teachers[1] = new Teacher("Ms. Sarah Johnson", "Physics");

        // Create staff members using constructor
        staffMembers[0] = new Staff("Mr. Robert Davis", "Librarian");
        staffMembers[1] = new Staff("Ms. Emily Brown", "Administrative Assistant");

        // Create courses using constructor
        courses[0] = new Course("Mathematics");
        courses[1] = new Course("Physics");
        courses[2] = new Course("Chemistry");

        System.out.println("===== Student Details =====");
        for (Student s : students) {
            s.displayDetails();
            System.out.println();
        }

        System.out.println("===== Teacher Details =====");
        for (Teacher t : teachers) {
            t.displayDetails();
            System.out.println();
        }

        System.out.println("===== Staff Details =====");
        for (Staff s : staffMembers) {
            s.displayDetails();
            System.out.println();
        }

        System.out.println("===== Course Details =====");
        for (Course c : courses) {
            c.displayDetails();
            System.out.println();
        }

        // Attendance Recording
        System.out.println("===== Attendance Records =====");
        List<AttendanceRecord> attendanceLog = new ArrayList<>();

        // Updated to use inherited getId() method
        attendanceLog.add(new AttendanceRecord(students[0].getId(), courses[0].getCourseId(), "Present"));
        attendanceLog.add(new AttendanceRecord(students[1].getId(), courses[1].getCourseId(), "Absent"));
        attendanceLog.add(new AttendanceRecord(students[2].getId(), courses[2].getCourseId(), "Late"));

        // Display attendance records
        for (AttendanceRecord record : attendanceLog) {
            record.displayRecord();
        }

        // Persistence: Save data to files
        System.out.println("\n===== File Storage & Persistence =====");

        // Create lists for storage
        List<Storable> studentList = new ArrayList<>();
        for (Student s : students) {
            studentList.add(s);
        }

        List<Storable> courseList = new ArrayList<>();
        for (Course c : courses) {
            courseList.add(c);
        }

        List<Storable> attendanceList = new ArrayList<>();
        for (AttendanceRecord record : attendanceLog) {
            attendanceList.add(record);
        }

        // Save data to files using FileStorageService
        FileStorageService.saveData(studentList, "students.txt");
        FileStorageService.saveData(courseList, "courses.txt");
        FileStorageService.saveData(attendanceList, "attendance_log.txt");

        System.out.println("\n✓ All data has been persisted to files successfully!");
    }
}

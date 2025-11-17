package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Demonstrates polymorphism by displaying details of all people in the school
     * directory.
     * This method takes a List<Person> and calls displayDetails() on each person.
     * The correct overridden method for each specific object type (Student,
     * Teacher, Staff)
     * is executed at runtime - this is runtime polymorphism in action.
     * 
     * @param people A list of Person objects (containing Student, Teacher, Staff
     *               instances)
     */
    public static void displaySchoolDirectory(List<Person> people) {
        System.out.println("===== SCHOOL DIRECTORY (Polymorphic Display) =====");
        for (Person person : people) {
            // The correct displayDetails() method is called based on the actual object type
            person.displayDetails();
            System.out.println();
        }
    }

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

        // Demonstrate Polymorphism: Create a List<Person> containing different object
        // types
        List<Person> schoolDirectory = new ArrayList<>();

        // Add students
        for (Student s : students) {
            schoolDirectory.add(s);
        }

        // Add teachers
        for (Teacher t : teachers) {
            schoolDirectory.add(t);
        }

        // Add staff members
        for (Staff s : staffMembers) {
            schoolDirectory.add(s);
        }

        // Call displaySchoolDirectory to show polymorphic behavior
        displaySchoolDirectory(schoolDirectory);

        // Attendance Recording
        System.out.println("===== Attendance Records =====");
        List<AttendanceRecord> attendanceLog = new ArrayList<>();

        // Updated to use Student and Course objects instead of just IDs
        attendanceLog.add(new AttendanceRecord(students[0], courses[0], "Present"));
        attendanceLog.add(new AttendanceRecord(students[1], courses[1], "Absent"));
        attendanceLog.add(new AttendanceRecord(students[2], courses[2], "Present"));

        // Display attendance records
        for (AttendanceRecord record : attendanceLog) {
            record.displayRecord();
        }

        // Persistence: Save data to files
        System.out.println("\n===== File Storage & Persistence =====");

        // Create lists for storage
        // Use instanceof and casting to filter only Student objects (which implement
        // Storable)
        List<Storable> studentList = new ArrayList<>();
        for (Person person : schoolDirectory) {
            if (person instanceof Student) {
                studentList.add((Student) person);
            }
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

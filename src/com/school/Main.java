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

        // ===== Part 8: Attendance Service with Overloaded Methods =====
        System.out.println("\n===== Part 8: Overloaded Commands - AttendanceService =====");

        // Create AttendanceService to manage attendance records
        AttendanceService attendanceService = new AttendanceService();

        // Convert students array to list for lookups
        List<Student> allStudents = new ArrayList<>();
        for (Student s : students) {
            allStudents.add(s);
        }

        // Convert courses array to list for lookups
        List<Course> allCourses = new ArrayList<>();
        for (Course c : courses) {
            allCourses.add(c);
        }

        System.out.println("\n----- Using Overloaded markAttendance (Version 1: Direct Objects) -----");
        // Version 1: Using Student and Course objects directly
        attendanceService.markAttendance(students[0], courses[0], "Present");
        attendanceService.markAttendance(students[1], courses[1], "Absent");
        attendanceService.markAttendance(students[2], courses[2], "Present");

        System.out.println("\n----- Using Overloaded markAttendance (Version 2: ID Lookups) -----");
        // Version 2: Using student and course IDs (performs lookups)
        attendanceService.markAttendance(students[3].getId(), courses[0].getCourseId(), "Present",
                allStudents, allCourses);
        attendanceService.markAttendance(students[0].getId(), courses[1].getCourseId(), "Absent",
                allStudents, allCourses);

        System.out.println("\n----- Using Overloaded displayAttendanceLog (Version 1: All Records) -----");
        // Version 1: Display all attendance records
        attendanceService.displayAttendanceLog();

        System.out.println("\n----- Using Overloaded displayAttendanceLog (Version 2: Filter by Student) -----");
        // Version 2: Display records for a specific student using Streams
        attendanceService.displayAttendanceLog(students[0]);

        System.out.println("\n----- Using Overloaded displayAttendanceLog (Version 3: Filter by Course) -----");
        // Version 3: Display records for a specific course using Streams
        attendanceService.displayAttendanceLog(courses[0]);

        // Persistence: Save data to files
        System.out.println("\n===== File Storage & Persistence =====");

        // Create lists for storage
        // Use instanceof and casting to filter only Student objects (which implement
        // Storable)
        List<Storable> storableStudentList = new ArrayList<>();
        for (Person person : schoolDirectory) {
            if (person instanceof Student) {
                storableStudentList.add((Student) person);
            }
        }

        List<Storable> storableCourseList = new ArrayList<>();
        for (Course c : courses) {
            storableCourseList.add(c);
        }

        // Save attendance records from AttendanceService
        List<Storable> attendanceList = new ArrayList<>(attendanceService.getAttendanceLog());

        // Save data to files using FileStorageService
        FileStorageService.saveData(storableStudentList, "students.txt");
        FileStorageService.saveData(storableCourseList, "courses.txt");
        FileStorageService.saveData(attendanceList, "attendance_log.txt");

        System.out.println("\n✓ All data has been persisted to files successfully!");
    }
}

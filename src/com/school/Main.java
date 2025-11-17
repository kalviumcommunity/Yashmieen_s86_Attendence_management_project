package com.school;

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
        // Create a registration service to manage entities
        RegistrationService registrationService = new RegistrationService();

        // Create and register students
        Student s1 = new Student("Alice", 10);
        Student s2 = new Student("Bob", 9);
        Student s3 = new Student("Charlie", 10);
        Student s4 = new Student("Diana", 9);
        registrationService.registerStudent(s1);
        registrationService.registerStudent(s2);
        registrationService.registerStudent(s3);
        registrationService.registerStudent(s4);

        // Create and register teachers
        Teacher t1 = new Teacher("Mr. John Smith", "Mathematics");
        Teacher t2 = new Teacher("Ms. Sarah Johnson", "Physics");
        registrationService.registerTeacher(t1);
        registrationService.registerTeacher(t2);

        // Create and register staff members
        Staff st1 = new Staff("Mr. Robert Davis", "Librarian");
        Staff st2 = new Staff("Ms. Emily Brown", "Administrative Assistant");
        registrationService.registerStaff(st1);
        registrationService.registerStaff(st2);

        // Create and register courses with capacity
        // Mathematics course capacity 2
        Course c1 = registrationService.createCourse("Mathematics", 2);
        // Physics capacity 3
        Course c2 = registrationService.createCourse("Physics", 3);
        // Chemistry capacity 1
        Course c3 = registrationService.createCourse("Chemistry", 1);

        // Display combined directory via RegistrationService
        displaySchoolDirectory(registrationService.getAllPeople());

        // ===== Part 8: Attendance Service with Overloaded Methods =====
        System.out.println("\n===== Part 8: Overloaded Commands - AttendanceService =====");

        // Create AttendanceService to manage attendance records (depends on
        // RegistrationService)
        AttendanceService attendanceService = new AttendanceService(registrationService);

        System.out.println("\n----- Using Overloaded markAttendance (Version 1: Direct Objects) -----");
        // Version 1: Using Student and Course objects directly
        attendanceService.markAttendance(s1, c1, "Present");
        attendanceService.markAttendance(s2, c2, "Absent");
        attendanceService.markAttendance(s3, c3, "Present");

        System.out.println("\n----- Using Overloaded markAttendance (Version 2: ID Lookups) -----");
        // Version 2: Using student and course IDs (performs lookups)
        attendanceService.markAttendance(s4.getId(), c1.getCourseId(), "Present");
        attendanceService.markAttendance(s1.getId(), c2.getCourseId(), "Absent");

        System.out.println("\n----- Using Overloaded displayAttendanceLog (Version 1: All Records) -----");
        // Version 1: Display all attendance records
        attendanceService.displayAttendanceLog();

        System.out.println("\n----- Using Overloaded displayAttendanceLog (Version 2: Filter by Student) -----");
        // Version 2: Display records for a specific student using Streams
        attendanceService.displayAttendanceLog(s1);

        System.out.println("\n----- Using Overloaded displayAttendanceLog (Version 3: Filter by Course) -----");
        // Version 3: Display records for a specific course using Streams
        attendanceService.displayAttendanceLog(c1);

        // Persistence: Save data to files
        System.out.println("\n===== File Storage & Persistence =====");
        // ===== Part 10: Course capacity and enrollment demo =====
        System.out.println("\n===== Part 10: Course Capacity & Enrollment Demo =====");

        // Attempt to enroll students into Mathematics (capacity 2)
        registrationService.enrollStudentInCourse(s1, c1); // should succeed
        registrationService.enrollStudentInCourse(s2, c1); // should succeed
        registrationService.enrollStudentInCourse(s3, c1); // should fail - capacity reached

        // Show updated course info
        System.out.println();
        c1.displayDetails();

        // Save registered entities using RegistrationService
        registrationService.saveStudents("students.txt");
        registrationService.saveCourses("courses.txt");
        registrationService.saveTeachers("teachers.txt");
        registrationService.saveStaff("staff.txt");

        // Save attendance records from AttendanceService
        attendanceService.saveAttendanceLog("attendance_log.txt");

        System.out.println("\n✓ All data has been persisted to files successfully!");
    }
}

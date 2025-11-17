package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AttendanceService class encapsulates all attendance-related logic.
 * This class manages a list of AttendanceRecord objects and provides
 * overloaded methods for marking attendance and displaying records.
 * Demonstrates the use of method overloading for flexible API design.
 */
public class AttendanceService {
    private List<AttendanceRecord> attendanceLog;
    private RegistrationService registrationService;

    /**
     * Constructor for AttendanceService
     * Initializes an empty attendance log
     */
    public AttendanceService() {
        this.attendanceLog = new ArrayList<>();
        this.registrationService = null;
    }

    /**
     * Construct AttendanceService with a RegistrationService dependency for lookups
     */
    public AttendanceService(RegistrationService registrationService) {
        this.attendanceLog = new ArrayList<>();
        this.registrationService = registrationService;
    }

    /**
     * Get all attendance records
     * 
     * @return List of all AttendanceRecord objects
     */
    public List<AttendanceRecord> getAttendanceLog() {
        return attendanceLog;
    }

    /**
     * Overloaded markAttendance method (Version 1)
     * Accepts Student and Course objects directly.
     * This is the most convenient way to mark attendance when you already have
     * the Student and Course objects.
     * 
     * @param student The Student object
     * @param course  The Course object
     * @param status  The attendance status ("Present" or "Absent")
     */
    public void markAttendance(Student student, Course course, String status) {
        AttendanceRecord record = new AttendanceRecord(student, course, status);
        attendanceLog.add(record);
        System.out.println("✓ Attendance marked: " + student.getName() + " - " + course.getCourseName()
                + " - " + status);
    }

    /**
     * Overloaded markAttendance method (Version 2)
     * Performs lookups using student ID and course ID.
     * This is useful when you only have the IDs and need to look up the actual
     * objects from the provided lists.
     * 
     * @param studentId   The ID of the student
     * @param courseId    The ID of the course
     * @param status      The attendance status ("Present" or "Absent")
     * @param allStudents List of all available Student objects for lookup
     * @param allCourses  List of all available Course objects for lookup
     * @return true if attendance was marked successfully, false if student or
     *         course not found
     */
    /**
     * Lookup-based markAttendance that uses RegistrationService for lookups.
     * Returns false if the registrationService is missing or lookups fail.
     */
    public boolean markAttendance(int studentId, int courseId, String status) {
        if (this.registrationService == null) {
            System.out.println("✗ Error: RegistrationService not configured for AttendanceService.");
            return false;
        }

        Student student = registrationService.findStudentById(studentId);
        Course course = registrationService.findCourseById(courseId);

        if (student == null) {
            System.out.println("✗ Error: Student with ID " + studentId + " not found.");
            return false;
        }

        if (course == null) {
            System.out.println("✗ Error: Course with ID " + courseId + " not found.");
            return false;
        }

        markAttendance(student, course, status);
        return true;
    }

    /**
     * Overloaded displayAttendanceLog method (Version 1)
     * Displays all attendance records in the log.
     * Shows the complete attendance history without any filtering.
     */
    public void displayAttendanceLog() {
        if (attendanceLog.isEmpty()) {
            System.out.println("⚠️ No attendance records found.");
            return;
        }

        System.out.println("===== Complete Attendance Log =====");
        for (AttendanceRecord record : attendanceLog) {
            record.displayRecord();
        }
        System.out.println("Total Records: " + attendanceLog.size());
    }

    /**
     * Overloaded displayAttendanceLog method (Version 2)
     * Displays attendance records filtered by a specific Student.
     * Uses Java Streams for filtering.
     * 
     * @param student The Student object to filter by
     */
    public void displayAttendanceLog(Student student) {
        // Use streams to filter records for the specific student
        List<AttendanceRecord> studentRecords = attendanceLog.stream()
                .filter(record -> record.getStudent().getId() == student.getId())
                .collect(Collectors.toList());

        if (studentRecords.isEmpty()) {
            System.out.println("⚠️ No attendance records found for student: " + student.getName());
            return;
        }

        System.out.println("===== Attendance Log for Student: " + student.getName() + " =====");
        for (AttendanceRecord record : studentRecords) {
            record.displayRecord();
        }
        System.out.println("Total Records: " + studentRecords.size());
    }

    /**
     * Overloaded displayAttendanceLog method (Version 3)
     * Displays attendance records filtered by a specific Course.
     * Uses Java Streams for filtering.
     * 
     * @param course The Course object to filter by
     */
    public void displayAttendanceLog(Course course) {
        // Use streams to filter records for the specific course
        List<AttendanceRecord> courseRecords = attendanceLog.stream()
                .filter(record -> record.getCourse().getCourseId() == course.getCourseId())
                .collect(Collectors.toList());

        if (courseRecords.isEmpty()) {
            System.out.println("⚠️ No attendance records found for course: " + course.getCourseName());
            return;
        }

        System.out.println("===== Attendance Log for Course: " + course.getCourseName() + " =====");
        for (AttendanceRecord record : courseRecords) {
            record.displayRecord();
        }
        System.out.println("Total Records: " + courseRecords.size());
    }

    /**
     * Save the attendance log to a file using FileStorageService
     * 
     * @param filename The name of the file to save to
     */
    public void saveAttendanceLog(String filename) {
        List<Storable> storableList = new ArrayList<>();
        for (AttendanceRecord r : attendanceLog)
            storableList.add(r);
        FileStorageService.saveData(storableList, filename);
    }
}

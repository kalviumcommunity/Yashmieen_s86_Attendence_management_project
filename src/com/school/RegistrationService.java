package com.school;

import java.util.ArrayList;
import java.util.List;

/**
 * RegistrationService handles registration and lookup of Students, Teachers,
 * Staff and Courses. It also provides saving utilities for those entities.
 */
public class RegistrationService {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Staff> staffMembers;
    private List<Course> courses;

    public RegistrationService() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Registration methods
    public void registerStudent(Student s) {
        if (s != null)
            students.add(s);
    }

    public void registerTeacher(Teacher t) {
        if (t != null)
            teachers.add(t);
    }

    public void registerStaff(Staff s) {
        if (s != null)
            staffMembers.add(s);
    }

    public void registerCourse(Course c) {
        if (c != null)
            courses.add(c);
    }

    /**
     * Create a course with a capacity and register it with the service.
     * Returns the created Course object.
     */
    public Course createCourse(String name, int capacity) {
        Course c = new Course(name, capacity);
        registerCourse(c);
        return c;
    }

    /**
     * Attempt to enroll a student into a course. Checks against course
     * capacity and prevents duplicate enrollments.
     * Returns true if enrollment succeeded.
     */
    public boolean enrollStudentInCourse(Student student, Course course) {
        if (student == null || course == null) {
            System.out.println("✗ Error: Student or Course is null.");
            return false;
        }

        // Ensure both student and course are known to this service
        if (!students.contains(student)) {
            System.out.println("⚠️ Student not registered with RegistrationService. Registering now.");
            registerStudent(student);
        }
        if (!courses.contains(course)) {
            System.out.println("⚠️ Course not registered with RegistrationService. Registering now.");
            registerCourse(course);
        }

        boolean enrolled = course.enrollStudent(student);
        if (enrolled) {
            System.out.println("✓ Enrollment successful: " + student.getName() + " -> " + course.getCourseName());
        } else {
            if (course.getEnrolledCount() >= course.getCapacity()) {
                System.out.println("✗ Enrollment failed: Course '" + course.getCourseName() + "' is full (capacity: "
                        + course.getCapacity() + ").");
            } else {
                System.out.println(
                        "✗ Enrollment failed: Student may already be enrolled in '" + course.getCourseName() + "'.");
            }
        }
        return enrolled;
    }

    // Lookup helpers
    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public Course findCourseById(int courseId) {
        for (Course c : courses) {
            if (c.getCourseId() == courseId)
                return c;
        }
        return null;
    }

    // Getters for lists
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers);
    }

    public List<Staff> getAllStaff() {
        return new ArrayList<>(staffMembers);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    /**
     * Convenience to get a combined people list for display (students, teachers,
     * staff)
     */
    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        people.addAll(students);
        people.addAll(teachers);
        people.addAll(staffMembers);
        return people;
    }

    // Persistence helpers
    public void saveStudents(String filename) {
        List<Storable> list = new ArrayList<>();
        for (Student s : students)
            list.add(s);
        FileStorageService.saveData(list, filename);
    }

    public void saveCourses(String filename) {
        List<Storable> list = new ArrayList<>();
        for (Course c : courses)
            list.add(c);
        FileStorageService.saveData(list, filename);
    }

    public void saveTeachers(String filename) {
        List<Storable> list = new ArrayList<>();
        for (Teacher t : teachers)
            list.add(t);
        FileStorageService.saveData(list, filename);
    }

    public void saveStaff(String filename) {
        List<Storable> list = new ArrayList<>();
        for (Staff s : staffMembers)
            list.add(s);
        FileStorageService.saveData(list, filename);
    }

    /**
     * Save all registered entities to their respective files.
     */
    public void saveAll(String studentsFile, String coursesFile, String teachersFile, String staffFile) {
        saveStudents(studentsFile);
        saveCourses(coursesFile);
        saveTeachers(teachersFile);
        saveStaff(staffFile);
    }
}

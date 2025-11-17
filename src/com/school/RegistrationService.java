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

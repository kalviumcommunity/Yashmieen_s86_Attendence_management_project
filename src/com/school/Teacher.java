package com.school;

/**
 * Teacher class extending Person
 * Represents a teaching staff member in the school.
 */
public class Teacher extends Person implements Storable {
    private String subjectTaught;

    /**
     * Constructor for Teacher class
     * 
     * @param name          The name of the teacher
     * @param subjectTaught The subject taught by this teacher
     */
    public Teacher(String name, String subjectTaught) {
        super(name);
        this.subjectTaught = subjectTaught;
    }

    // Getter for subject
    public String getSubjectTaught() {
        return subjectTaught;
    }

    /**
     * Override displayDetails to show teacher-specific information
     */
    @Override
    public void displayDetails() {
        System.out.println("--- Teacher Details ---");
        System.out.println("Teacher ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Subject Taught: " + this.subjectTaught);
    }

    @Override
    public String toDataString() {
        return id + "," + name + "," + subjectTaught;
    }
}

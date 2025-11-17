package com.school;

/**
 * Base class for all people in the school system.
 * Provides common attributes and methods for Student, Teacher, and Staff.
 */
public class Person {
    private static int nextPersonIdCounter = 1;
    protected int id;
    protected String name;

    /**
     * Constructor for Person class
     * 
     * @param name The name of the person
     */
    public Person(String name) {
        this.id = nextPersonIdCounter++;
        this.name = name;
    }

    // Getters for encapsulation
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Display details of the person (to be overridden in subclasses)
     */
    public void displayDetails() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
    }
}

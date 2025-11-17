package com.school;

/**
 * Staff class extending Person
 * Represents non-teaching staff member in the school.
 */
public class Staff extends Person implements Storable {
    private String role;

    /**
     * Constructor for Staff class
     * 
     * @param name The name of the staff member
     * @param role The role/position of this staff member
     */
    public Staff(String name, String role) {
        super(name);
        this.role = role;
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    /**
     * Override displayDetails to show staff-specific information
     */
    @Override
    public void displayDetails() {
        System.out.println("--- Staff Details ---");
        System.out.println("Staff ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Role: " + this.role);
    }

    @Override
    public String toDataString() {
        return id + "," + name + "," + role;
    }
}

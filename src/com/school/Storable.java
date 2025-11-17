package com.school;

/**
 * Storable interface for objects that can be persisted to storage.
 * Defines a contract for converting objects to a string format suitable for
 * file storage.
 */
public interface Storable {
    /**
     * Convert the object to a string representation suitable for data storage (CSV
     * format).
     * 
     * @return A CSV formatted string representation of the object
     */
    String toDataString();
}

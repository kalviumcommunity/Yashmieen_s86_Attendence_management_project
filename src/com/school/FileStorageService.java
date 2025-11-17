package com.school;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * FileStorageService class for persisting Storable objects to files.
 * Provides utility methods for saving data to text files in CSV format.
 */
public class FileStorageService {

    /**
     * Save a list of Storable objects to a file in CSV format.
     * Uses try-with-resources for safe file handling.
     * 
     * @param items    List of Storable objects to be saved
     * @param filename The name of the file to write to
     */
    public static void saveData(List<? extends Storable> items, String filename) {
        if (items == null || items.isEmpty()) {
            System.out.println("⚠️ Warning: No data to save for file: " + filename);
            return;
        }

        try (FileWriter fileWriter = new FileWriter(filename);
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            for (Storable item : items) {
                printWriter.println(item.toDataString());
            }

            System.out.println("✓ Successfully saved " + items.size() + " record(s) to " + filename);

        } catch (IOException e) {
            System.out.println("✗ Error saving data to file: " + filename);
            System.out.println("  Exception: " + e.getMessage());
        }
    }
}

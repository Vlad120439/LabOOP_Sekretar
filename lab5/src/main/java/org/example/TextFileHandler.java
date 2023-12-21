package org.example;

import java.io.*;

public class TextFileHandler {
    public static void saveToTextFile(String fileName, EmployeeDatabase employeeDatabase) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(employeeDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EmployeeDatabase loadFromTextFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (EmployeeDatabase) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
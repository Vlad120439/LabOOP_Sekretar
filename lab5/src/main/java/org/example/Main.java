package org.example;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Employee Management Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Filter Employees by Years of Employment");
            System.out.println("4. Filter Employees by Salary Range");
            System.out.println("5. Filter Employees by Position");
            System.out.println("6. Save to Text File");
            System.out.println("7. Load from Text File");
            System.out.println("8. Save to Binary File");
            System.out.println("9. Load from Binary File");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner, employeeDatabase);
                    break;
                case 2:
                    removeEmployee(scanner, employeeDatabase);
                    break;
                case 3:
                    filterEmployeesByYears(scanner, employeeDatabase);
                    break;
                case 4:
                    filterEmployeesBySalaryRange(scanner, employeeDatabase);
                    break;
                case 5:
                    filterEmployeesByPosition(scanner, employeeDatabase);
                    break;
                case 6:
                    saveToTextFile(scanner, employeeDatabase);
                    break;
                case 7:
                    loadFromTextFile(scanner, employeeDatabase);
                    break;
                case 8:
                    saveToBinaryFile(scanner, employeeDatabase);
                    break;
                case 9:
                    loadFromBinaryFile(scanner, employeeDatabase);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid. Please try again");
            }
        }
    }
    private static void addEmployee(Scanner scanner, EmployeeDatabase employeeDatabase) throws ParseException {
        System.out.println("Enter employee:");

        System.out.print("Last Name: ");
        String lastName = scanner.next();

        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Position: ");
        String position = scanner.next();

        System.out.print("Date of Birth (dd-MM-yyyy): ");
        String dateOfBirthStr = scanner.next();
        Date dateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirthStr);

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.next();

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Current Salary: ");
        double currentSalary = scanner.nextDouble();

        System.out.print("Date of Employment (dd-MM-yyyy): ");
        String dateOfEmploymentStr = scanner.next();
        Date dateOfEmployment = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfEmploymentStr);

        int id = employeeDatabase.getEmployees().size() + 1;

        Employee newEmployee = new Employee(id, lastName, firstName, position, dateOfBirth, phoneNumber, email, currentSalary, dateOfEmployment);
        employeeDatabase.addEmployee(newEmployee);

        System.out.println("Employee added");
    }
    private static void removeEmployee(Scanner scanner, EmployeeDatabase employeeDatabase) {
        System.out.print("Enter the ID of the employee to remove: ");
        int employeeId = scanner.nextInt();

        List<Employee> employees = employeeDatabase.getEmployees();
        Employee employeeToRemove = null;

        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove != null) {
            employeeDatabase.removeEmployee(employeeToRemove);
            System.out.println("Employee removed");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found in the database.");
        }
    }

    private static void filterEmployeesByYears(Scanner scanner, EmployeeDatabase employeeDatabase) {
        System.out.print("Enter the number of years: ");
        int years = scanner.nextInt();

        List<Employee> filteredEmployees = employeeDatabase.filterEmployeesByYears(years);

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found with " + years + " or more years of employment.");
        } else {
            System.out.println("Employees with " + years + " or more years of employment:");
            for (Employee employee : filteredEmployees) {
                System.out.println(employee);
            }
        }
    }

    private static void filterEmployeesBySalaryRange(Scanner scanner, EmployeeDatabase employeeDatabase) {
        System.out.print("Enter the minimum salary: ");
        double minSalary = scanner.nextDouble();
        System.out.print("Enter the maximum salary: ");
        double maxSalary = scanner.nextDouble();

        List<Employee> filteredEmployees = employeeDatabase.filterEmployeesBySalaryRange(minSalary, maxSalary);

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found within the salary range of " + minSalary + " to " + maxSalary);
        } else {
            System.out.println("Employees within the salary range of " + minSalary + " to " + maxSalary + ":");
            for (Employee employee : filteredEmployees) {
                System.out.println(employee);
            }
        }
    }

    private static void filterEmployeesByPosition(Scanner scanner, EmployeeDatabase employeeDatabase) {
        System.out.print("Enter the position to filter by: ");
        String position = scanner.next();

        List<Employee> filteredEmployees = employeeDatabase.filterEmployeesByPosition(position);

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found with the position: " + position);
        } else {
            System.out.println("Employees with the position: " + position + ":");
            for (Employee employee : filteredEmployees) {
                System.out.println(employee);
            }
        }
    }

    private static void saveToTextFile(Scanner scanner, EmployeeDatabase employeeDatabase) {
        System.out.print("Enter the filename to save the data: ");
        String filename = scanner.next();

        TextFileHandler.saveToTextFile(filename, employeeDatabase);

        System.out.println("Data saved to the text file: " + filename);
    }

    private static void loadFromTextFile(Scanner scanner, EmployeeDatabase employeeDatabase) throws IOException {
        System.out.print("Enter the filename to load data from: ");
        String filename = scanner.next();

        EmployeeDatabase loadedDatabase = TextFileHandler.loadFromTextFile(filename);

        if (loadedDatabase != null) {
            employeeDatabase = loadedDatabase;
            System.out.println("Data loaded from the text file: " + filename);
        } else {
            System.out.println("Failed to load data from the text file.");
        }
    }

    private static void saveToBinaryFile(Scanner scanner, EmployeeDatabase employeeDatabase) {
        System.out.print("Enter the filename to save data to (binary): ");
        String filename = scanner.next();

        BinaryFileHandler.saveToBinaryFile(filename, employeeDatabase);

        System.out.println("Data saved to the binary file: " + filename);
    }

    private static void loadFromBinaryFile(Scanner scanner, EmployeeDatabase employeeDatabase) {
        System.out.print("Enter the filename to load data from (binary): ");
        String filename = scanner.next();

        EmployeeDatabase loadedDatabase = BinaryFileHandler.loadFromBinaryFile(filename);

        if (loadedDatabase != null) {
            employeeDatabase = loadedDatabase;
            System.out.println("Data loaded from the binary file: " + filename);
        } else {
            System.out.println("Failed to load data from the binary file.");
        }
    }
}
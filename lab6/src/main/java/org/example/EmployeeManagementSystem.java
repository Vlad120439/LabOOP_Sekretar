package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EmployeeManagementSystem {
    private List<Employee> employees;
    private CharSequence dateOfEmployment;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(int employeeId) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == employeeId) {
                iterator.remove();
                break;
            }
        }
    }

    public void serializeToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(employees);
            System.out.println("Employees saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error while writing to the file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void deserializeFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Employees loaded from " + fileName + " successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading from the file: " + e.getMessage());
        }
    }

    public List<Employee> filterEmployeesByYears(int years) {
        List<Employee> filteredEmployees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (Employee employee : employees) {
            Date dateOfEmploymentString = employee.getDateOfEmployment();
            System.out.println("Date of Employment String: " + dateOfEmploymentString);

            try {
                System.out.print("Enter the date format for " + dateOfEmploymentString + ": ");
                String dateFormat = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

                LocalDate employmentDate = LocalDate.parse(dateOfEmployment, formatter);

                int yearsOfWork = LocalDate.now().getYear() - employmentDate.getYear();

                if (yearsOfWork >= years) {
                    filteredEmployees.add(employee);
                }
            } catch (Exception e) {
                System.out.println("Error parsing date for employee: " + employee.getId());
            }
        }

        return filteredEmployees;
    }

    public List<Employee> filterEmployeesBySalary(double minSalary, double maxSalary) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            double currentSalary = employee.getCurrentSalary();
            if (currentSalary >= minSalary && currentSalary <= maxSalary) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public List<Employee> filterEmployeesByPosition(String position) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public void addEmployeeWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee Details:");

        System.out.print("Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Position: ");
        String position = scanner.nextLine();

        System.out.print("Date of Birth (dd-mm-yyyy): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Current Salary: ");
        double currentSalary = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Date of Employment (dd-mm-yyyy): ");
        String dateOfEmployment = scanner.nextLine();

        Employee newEmployee = new Employee();

        employees.add(newEmployee);

        System.out.println("Employee added successfully.");
    }

    public void removeEmployeeWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Employee ID to remove: ");
        int employeeIdToRemove = scanner.nextInt();
        scanner.nextLine();

        Iterator<Employee> iterator = employees.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == employeeIdToRemove) {
                iterator.remove();
                removed = true;
                System.out.println("Employee with ID " + employeeIdToRemove + " removed successfully.");
                break;
            }
        }

        if (!removed) {
            System.out.println("Employee with ID " + employeeIdToRemove + " not found.");
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEmployee Management System Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display Employees by Years of Employment");
            System.out.println("4. Display Employees by Salary Range");
            System.out.println("5. Display Employees by Position");
            System.out.println("6. Save Employees to File");
            System.out.println("7. Load Employees from File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add Employee
                    addEmployeeWithUserInput();
                    break;
                case 2:
                    // Remove Employee
                    removeEmployeeWithUserInput();
                    break;
                case 3:
                    System.out.print("Enter the number of years: ");
                    int years = scanner.nextInt();
                    List<Employee> employeesByYears = filterEmployeesByYears(years);
                    displayEmployees(employeesByYears);
                    break;
                case 4:
                    System.out.print("Enter the minimum salary: ");
                    double minSalary = scanner.nextDouble();
                    System.out.print("Enter the maximum salary: ");
                    double maxSalary = scanner.nextDouble();
                    List<Employee> employeesBySalary = filterEmployeesBySalary(minSalary, maxSalary);
                    displayEmployees(employeesBySalary);
                    break;
                case 5:
                    System.out.print("Enter the position: ");
                    String position = scanner.nextLine();
                    List<Employee> employeesByPosition = filterEmployeesByPosition(position);
                    displayEmployees(employeesByPosition);
                    break;
                case 6:
                    System.out.print("Enter the file name to save: ");
                    String saveFileName = scanner.nextLine();
                    serializeToFile(saveFileName);
                    break;
                case 7:
                    System.out.print("Enter the file name to load: ");
                    String loadFileName = scanner.nextLine();
                    deserializeFromFile(loadFileName);
                    break;
                case 8:
                    System.out.println("Exiting the Employee Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 8);
    }

    private void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.displayMenu();
    }
}
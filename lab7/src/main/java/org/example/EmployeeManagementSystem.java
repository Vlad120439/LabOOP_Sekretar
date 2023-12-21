package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeManagementSystem {
    private Set<Employee> employees;
    private CharSequence dateOfEmployment;

    public EmployeeManagementSystem() {
        employees = new HashSet<>();
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
            employees = (Set<Employee>) ois.readObject();
            System.out.println("Employees loaded from " + fileName + " successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading from the file: " + e.getMessage());
        }
    }

    public Set<Employee> filterEmployeesByYears(int years) {
        Set<Employee> filteredEmployees = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("your_actual_date_format_here");

        for (Employee employee : employees) {
            try {
                LocalDate employmentDate = LocalDate.parse(employee.getDateOfEmployment(), formatter);
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

    public Set<Employee> filterEmployeesBySalary(double minSalary, double maxSalary) {
        Set<Employee> filteredEmployees = new HashSet<>();
        for (Employee employee : employees) {
            double currentSalary = employee.getCurrentSalary();
            if (currentSalary >= minSalary && currentSalary <= maxSalary) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public Set<Employee> filterEmployeesByPosition(String position) {
        Set<Employee> filteredEmployees = new HashSet<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public Set<Employee> filterEmployeesByBirthMonth(String birthMonth) {
        List<Employee> filteredEmployees = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("your actual date format here");

        for (Employee employee : employees) {
            try {
                LocalDate birthDate = LocalDate.parse(employee.getDateOfBirth(), formatter);
                String employeeBirthMonth = String.valueOf(birthDate.getMonthValue());

                if (employeeBirthMonth.equals(birthMonth)) {
                    filteredEmployees.add(employee);
                }
            } catch (Exception e) {
                System.out.println("Error parsing date for employee: " + employee.getId());
            }
        }
        filteredEmployees.sort(Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName));

        return (Set<Employee>) filteredEmployees;
    }

    public Set<String> getUniquePositions() {
        Set<String> positions = new HashSet<>();
        for (Employee employee : employees) {
            positions.add(employee.getPosition());
        }
        return positions;
    }

    public Map<String, Long> getPositionsCount() {
        Map<String, Long> positionsCount = new HashMap<>();
        for (Employee employee : employees) {
            positionsCount.merge(employee.getPosition(), 1L, Long::sum);
        }
        return positionsCount;
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
            System.out.println("6. Display Employees by Birth Month");
            System.out.println("7. Display Unique Positions");
            System.out.println("8. Display Positions with Count");
            System.out.println("9. Save Employees to File");
            System.out.println("10. Load Employees from File");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployeeWithUserInput();
                    break;
                case 2:
                    removeEmployeeWithUserInput();
                    break;
                case 3:
                    displayEmployees(filterEmployeesByYears(getYearsFromUser()));
                    break;
                case 4:
                    displayEmployees(filterEmployeesBySalary(getMinSalaryFromUser(), getMaxSalaryFromUser()));
                    break;
                case 5:
                    displayEmployees(filterEmployeesByPosition(getPositionFromUser()));
                    break;
                case 6:
                    displayEmployees(filterEmployeesByBirthMonth(getBirthMonthFromUser()));
                    break;
                case 7:
                    displayPositions(getUniquePositions());
                    break;
                case 8:
                    displayPositionsCount(getPositionsCount());
                    break;
                case 9:
                    saveToFile(getFileNameFromUser());
                    break;
                case 10:
                    loadFromFile(getFileNameFromUser());
                    break;
                case 11:
                    System.out.println("Exiting the Employee Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 11);
    }

    private void displayEmployees(Set<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public int getYearsFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of years: ");
        return scanner.nextInt();
    }

    public double getMinSalaryFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the minimum salary: ");
        return scanner.nextDouble();
    }

    public double getMaxSalaryFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the maximum salary: ");
        return scanner.nextDouble();
    }

    public String getPositionFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the position: ");
        return scanner.nextLine();
    }

    public String getBirthMonthFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the birth month (as two digits): ");
        return scanner.nextLine();
    }

    public String getFileNameFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        return scanner.nextLine();
    }

    public void displayPositions(Set<String> positions) {
        System.out.println("\nPositions:");
        for (String position : positions) {
            System.out.println(position);
        }
    }

    public void displayPositionsCount(Map<String, Long> positionsCount) {
        System.out.println("\nPositions with Count:");
        for (Map.Entry<String, Long> entry : positionsCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Employee employee : employees) {
                writer.write(employee.toString());
                writer.newLine();
            }
            System.out.println("Data saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = Employee.fromString(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }
            System.out.println("Data loaded from file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.displayMenu();
    }
}

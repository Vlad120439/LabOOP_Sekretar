package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;

public class Main {
    public static void main(String[] args) throws ParseException {

        Employee[] employees = createEmployeeArray();

        System.out.println("Employees who have been working for 5 years or more:");
        displayEmployeesByYears(employees, 5);

        System.out.println("\nEmployees whose salary is in the range of $40000 to $60000:");
        displayEmployeesBySalaryRange(employees, 40000, 60000);

        System.out.println("\nEmployees in the position of Manager:");
        displayEmployeesByPosition(employees, "Manager");
    }

    private static Employee[] createEmployeeArray() throws ParseException {
        Employee[] employees = new Employee[4];
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 1; i++) {
            employees[0] = new Employee();

            System.out.println("Enter last name for Employee " + (i + 1) + ": ");
            employees[0].setLastName(scanner.nextLine());

            System.out.print("Enter first name for Employee " + (i + 1) + ": ");
            employees[0].setFirstName(scanner.nextLine());

            System.out.print("Enter position for Employee " + (i + 1) + ": ");
            employees[0].setPosition(scanner.nextLine());

            System.out.print("Enter date of birth (yyyy-mm-dd) for Employee " + (i + 1) + ": ");
            Date dateOfBirth = dateFormat.parse(scanner.nextLine());
            employees[0].setDateOfBirth(dateOfBirth);

            System.out.print("Enter phone number for Employee " + (i + 1) + ": ");
            employees[0].setPhoneNumber(scanner.nextLine());

            System.out.print("Enter email for Employee " + (i + 1) + ": ");
            employees[0].setEmail(scanner.nextLine());

            System.out.print("Enter current salary for Employee " + (i + 1) + ": ");
            double currentSalary = Double.parseDouble(scanner.nextLine());
            employees[0].setCurrentSalary(currentSalary);

            System.out.print("Enter date of employment (yyyy-mm-dd) for Employee " + (i + 1) + ": ");
            Date dateOfEmployment = dateFormat.parse(scanner.nextLine());
            employees[0].setDateOfEmployment(dateOfEmployment);
        }

        employees[0] = new Employee(1, employees[0].getLastName(), employees[0].getFirstName(), employees[0].getPosition(),
                employees[0].getDateOfBirth(), employees[0].getPhoneNumber(), employees[0].getEmail(),
                employees[0].getCurrentSalary(), employees[0].getDateOfEmployment());
        employees[1] = new Employee(2, "Gosling", "Ryan", "Manager",
                new Date(1980, 5, 15), "098-542-2874", "gosling@gmail.com",
                100000, new Date(2010, 8, 20));
        employees[2] = new Employee(3, "Sekretar", "Vladyslav", "Developer",
                new Date(2004, 6, 10), "096-666-5555", "vlad@gmail.com",
                60000, new Date(2022, 5, 5));
        employees[3] = new Employee(4, "Bateman", "Patrick", "Manager",
                new Date(1978, 10, 10), "044-444-4444", "bateman111@gmail.com",
                45000, new Date(2008, 12, 5));
        return employees;
    }

    private static void displayEmployeesByYears(Employee[] employees, int years) {
        for (Employee employee : employees) {
            int yearsOfEmployment = new Date().getYear() - employee.getDateOfEmployment().getYear();
            if (yearsOfEmployment >= years) {
                System.out.println(employee);
            }
        }
    }

    private static void displayEmployeesBySalaryRange(Employee[] employees, double minSalary, double maxSalary) {
        for (Employee employee : employees) {
            if (employee.getCurrentSalary() >= minSalary && employee.getCurrentSalary() <= maxSalary) {
                System.out.println(employee);
            }
        }
    }

    private static void displayEmployeesByPosition(Employee[] employees, String position) {
        for (Employee employee : employees) {
            if (employee.getPosition().equals(position)) {
                System.out.println(employee);
            }
        }
    }
}
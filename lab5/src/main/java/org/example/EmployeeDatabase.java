package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDatabase implements Serializable {
    private List<Employee> employees;

    public EmployeeDatabase() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> filterEmployeesByYears(int years) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            int yearsOfEmployment = new Date().getYear() - employee.getDateOfEmployment().getYear();
            if (yearsOfEmployment >= years) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public List<Employee> filterEmployeesBySalaryRange(double minSalary, double maxSalary) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getCurrentSalary() >= minSalary && employee.getCurrentSalary() <= maxSalary) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public List<Employee> filterEmployeesByPosition(String position) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equals(position)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }
}

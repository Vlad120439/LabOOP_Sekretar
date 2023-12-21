package org.example;

import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

class Employee implements Serializable {
    public int id;
    public String lastName;
    public String firstName;
    private String position;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private double currentSalary;
    private Date dateOfEmployment;

    public Employee(int id, String lastName, String firstName, String position, Date dateOfBirth, String phoneNumber, String email, double currentSalary, Date dateOfEmployment) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.currentSalary = currentSalary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public Employee() {
    }

    public static Employee fromString(String employeeString) {
        StringTokenizer tokenizer = new StringTokenizer(employeeString, ",");
        try {
            int id = Integer.parseInt(tokenizer.nextToken().trim());
            String lastName = tokenizer.nextToken().trim();
            String firstName = tokenizer.nextToken().trim();
            String position = tokenizer.nextToken().trim();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
            Date dateOfBirth = dateFormat.parse(tokenizer.nextToken().trim());
            String phoneNumber = tokenizer.nextToken().trim();
            String email = tokenizer.nextToken().trim();
            double currentSalary = Double.parseDouble(tokenizer.nextToken().trim());
            Date dateOfEmployment = dateFormat.parse(tokenizer.nextToken().trim());

            return new Employee(id, lastName, firstName, position, dateOfBirth,
                    phoneNumber, email, currentSalary, dateOfEmployment);
        } catch (ParseException | NumberFormatException | NoSuchElementException e) {
            System.out.println("Error parsing employee string: " + employeeString);
            return null;
        }
    }


    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public CharSequence getDateOfBirth() {
        return (CharSequence) dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public CharSequence getDateOfEmployment() {
        return (CharSequence) dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", position='" + position + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", currentSalary=" + currentSalary +
                ", dateOfEmployment=" + dateOfEmployment +
                '}';
    }
}

package org.example;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.Math.*;

import java.time.LocalDateTime;

public class Main {
    private double a = 0.7;
    private double b = 0.05;
    private double x = 0.5;

    public double findD() {
        return pow(x, 2) * (x + 1) / b + pow(3, sin(x - a));
    }

    public double findW() {
        return sqrt(b / a) + pow(2, cos(2)) * pow((x + b), 3);
    }

    public void println() {
        System.out.println("D= " + findD());
        System.out.println("W= " + findW());
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number a - ");
        a = scanner.nextDouble();

        System.out.print("Enter the number b - ");
        b = scanner.nextDouble();

        System.out.print("Enter the number x - ");
        x = scanner.nextDouble();
    }

    public void date() {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - 'T'");
        System.out.println(today.format(formatter));
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.date();
        main.println();
        main.inputData();
        main.println();
    }
}
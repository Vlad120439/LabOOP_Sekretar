package org.example;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main implements DoubleArrayReader, ArrayProcessor {
    @Override
    public double[] readOneDimensionalArray(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int size = scanner.nextInt();
            double[] array = new double[size];
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextDouble();
            }

            scanner.close();
            return array;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double[] readOneDimensionalArray(String fileName) {
//        File file = new File(fileName);
//        return readOneDimensionalArray(file);

        try {
            Scanner scanner = new Scanner(new File(fileName));
            int size = scanner.nextInt();
            double[] array = new double[size];
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextDouble();
            }

            scanner.close();
            return array;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double[][] readTwoDimensionalArray(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int rows = scanner.nextInt();
            double[][] array = new double[rows][rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < rows; j++) {
                    array[i][j] = scanner.nextDouble();
                }
            }

            scanner.close();
            return array;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double[][] readTwoDimensionalArray(String fileName) {
//        File file = new File(fileName);
//        return readTwoDimensionalArray(file);

        try {
            Scanner scanner = new Scanner(new File(fileName));
            int rows = scanner.nextInt();
            double[][] array = new double[rows][];
            for (int i = 0; i < rows; i++) {
                int columns = scanner.nextInt();
                array[i] = new double[columns];
                for (int j = 0; j < columns; j++) {
                    array[i][j] = scanner.nextDouble();
                }
            }

            scanner.close();
            return array;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double calculate(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The array is empty.");
        }

        double smallest = array[0];
        double largest = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < smallest) {
                smallest = array[i];
            } else if (array[i] > largest) {
                largest = array[i];
            }
        }

        double sum = smallest + largest;

        return sum;
    }

    @Override
    public double calculate(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The array is empty.");
        }

        int n = array.length;

        if (n <= 0) {
            throw new IllegalArgumentException("The matrix is empty.");
        }

        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (array[i][j] > 0) {
                    System.out.print("*");
                    sum += array[i][j];
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        return sum;
    }

    @Override
    public void processArray(double[] array) {
        System.out.print("One dimensional array: ");
        for (double element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    @Override
    public void processArray(double[][] array) {
        System.out.println("Two dimensional array:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
    }
}

    public static void main(String[] args)
    {
        Main main = new Main();
        double[] array = main.readOneDimensionalArray("src/main/java/org/example/lab41.txt");
        main.processArray(array);
        System.out.println(main.calculate(array));
        double[][] array2 = main.readTwoDimensionalArray(new File("src/main/java/org/example/lab42.txt"));
        main.processArray(array2);
        System.out.println(main.calculate(array2));
    }
}
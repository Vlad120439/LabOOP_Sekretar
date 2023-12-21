package org.example;

import java.util.Scanner;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the maximum string length: ");
        int maxLength = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        TextFormatter textFormatter = new TextFormatter();
        textFormatter.formatText(text, maxLength);

        String password = "Vl@dyslav19";
        boolean isValid = PasswordChecker.isPasswordValid(password);

        if (isValid)
            System.out.println("Password is valid.");
        else
            System.out.println("Password is invalid.");
    }
}

class TextFormatter {
    public void formatText(String text, int maxLength) {
        String[] words = text.split("\\s+");

        for (String word : words) {
            if (word.length() <= maxLength) {
                System.out.println(word);
            } else {
                int start = 0;
                while (start < word.length()) {
                    int end = Math.min(start + maxLength, word.length());
                    while (end < word.length() && !Character.isWhitespace(word.charAt(end)))
                        end--;

                    System.out.println(word.substring(start, end));
                    start = end;
                }
            }
        }
    }
}

class PasswordChecker {
    private static final int MIN_LENGTH = 8;

    public static boolean isPasswordValid(String password) {
        if (password.length() < MIN_LENGTH)
            return false;

        if (!Pattern.compile("[A-Z]").matcher(password).find())
            return false;

        if (!Pattern.compile("[a-z]").matcher(password).find())
            return false;

        if (!Pattern.compile("\\d").matcher(password).find())
            return false;

        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find())
            return false;

        return true;
    }
}

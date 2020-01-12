package com.bookworm;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Prompt {
    private iBook[] books;
    private int goal = 0;

    private static int scanIntBetween(String message, String errorMessage, int minimum, int maximum) {
        int value = minimum - 1;
        Scanner input = new Scanner(System.in);
        System.out.println(message + " >>> ");
        while (value < minimum || value > maximum) {
            try {
                value = input.nextInt();
                if (value < minimum || value > maximum) {
                    throw new InputMismatchException(String.format(errorMessage));
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println(e.getMessage());
                System.out.printf(">>> ");
            }
        }
        input.nextLine();
        return value;
    }

    private static int scanIntWithMinimum(String message, int minimum) {
        String errorMessage = "Value must be greater than " + minimum;
        return scanIntBetween(message, errorMessage, minimum, Integer.MAX_VALUE);
    }

    private static String scanString(String message) {
        String output;
        Scanner input = new Scanner(System.in);
        System.out.println(message + "\n>>> ");
        output = input.nextLine();
        return output;
    }

    private static boolean askYesOrNo(String question) {
        return askYesOrNo(question, false);
    }

    private static boolean askYesOrNo(String question, boolean defaultAnswer) {
        Scanner input = new Scanner(System.in);
        String answer = "o";
        System.out.println(question + " >>>");
        answer = input.nextLine();
        switch (answer.toUpperCase()) {
            case "Y": case "YES":
                return true;
            case "N": case "NO":
                return false;
            default:
                return defaultAnswer;
        }
    }


    public void setGoal() {
        if (this.goal <= 0) {
            System.out.println("You don't have a goal set. ");
        }
        this.goal = scanIntWithMinimum("How many books do you want to read this year?", 1);
    }

    // TODO: Add books
    // Ask if physical book, ebook or audiobook.
    // If book or audiobook, ask for the length.
    // Ask if book is complete or not
    // If not, ask how much is complete
    public void addIBook() {
        String title = setTitle();
        if (!title.equals("")) {
            boolean complete = askYesOrNo("Have you finished reading this book? [yes/NO]");
            String format = setFormat();
            switch (format) {
                case "book":
                    addBook(title, complete);
                    break;
                case "ebook":
                    addEbook(title, complete);
                    break;
                case "audiobook":
                    addAudiobook(title, complete);
                    break;
            }
        }
    }

    private void addBook(String title, boolean complete) {

    }

    private void addEbook(String title, boolean complete) {

    }

    private void addAudiobook(String title, boolean complete) {

    }

    private String setTitle() {
        return scanString("What is the title of the book? Leave blank to abort.");
    }

    private String setFormat() {
        System.out.println("What kind of book would you like to add? Type 0 to quit.");
        HashMap<Integer, String> formats = iBook.getFormats();
        for (int i = 1; i <= formats.size(); i++) {
            System.out.printf("[%d] %s%n", i,  formats.get(i-1));
        }
        int choice = scanIntBetween("", "Choose one of the numebrs above, type 0 to quit.", 0, 3);
        return choice != 0 ? formats.get(choice-1) : "";
    }
}

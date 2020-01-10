package com.bookworm;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Prompt {
    private iBook[] books;
    private int goal = 0;

    private int scanIntBetween(String message, String errorMessage, int minimum, int maximum) {
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
        return value;
    }

    private int scanIntWithMinimum(String message, int minimum) {
        String errorMessage = "Value must be greater than " + minimum;
        return scanIntBetween(message, errorMessage, minimum, Integer.MAX_VALUE);
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
    // Probably best to make an integer entering method.
    public void addIBook() {
        System.out.println("What kind of book would you like to add? Type 0 to quit.");
        HashMap<Integer, String> formats = iBook.getFormats();
        for (int i = 1; i <= formats.size(); i++) {
            System.out.printf("[%d] %s%n", i,  formats.get(i-1));
        }
        int choice = scanIntBetween("", "Choose one of the numebrs above, type 0 to quit.", 0, 3);
        if (choice != 0) {
            switch (formats.get(choice - 1)) {
                case "book":
                    addBook();
                    break;
                case "ebook":
                    addEbook();
                    break;
                case "audiobook":
                    addAudiobook();
                    break;
            }
        }
    }

    private void addAudiobook() {
    }

    private void addEbook() {
    }

    private void addBook() {
    }
}

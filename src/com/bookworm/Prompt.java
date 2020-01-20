package com.bookworm;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Prompt {
    // TODO: CRUD
    private ArrayList<iBook> books = new ArrayList<iBook>();
    private int goal = 0;
    private boolean nextDay = false;
    private Tracker tracker = new Tracker(goal, books);


    private static int scanIntBetween(String message, int minimum, int maximum) {
        String errorMessage = String.format("Enter a number between %d and %d", minimum, maximum);
        return scanIntBetween(message, errorMessage, minimum, maximum);
    }

    private static int scanIntWithMinimum(String message, int minimum) {
        String errorMessage = "Value must be greater than " + minimum;
        return scanIntBetween(message, errorMessage, minimum, Integer.MAX_VALUE);
    }

    private static int scanIntBetween(String message, String errorMessage, int minimum, int maximum) {
        int value = minimum - 1;
        Scanner input = new Scanner(System.in);
        System.out.printf("%s %n>>>", message);
        while (value < minimum || value > maximum) {
            try {
                value = input.nextInt();
                if (value < minimum || value > maximum) {
                    throw new InputMismatchException(errorMessage);
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println(e.getMessage());
                System.out.print(">>> ");
            }
        }
        input.nextLine();
        return value;
    }

    private static String scanString(String message) {
        String output;
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        System.out.print(">>> ");
        output = input.nextLine();
        return output;
    }

    private static int scanTime(String message) {
        int length = 0;
        while (length <= 0) {
            try {
                length = Audiobook.timeToSeconds(scanString(message+" (Enter text in format H:MM:SS or hours:minute:seconds)"));
            } catch (ParseException e) {
                System.out.println("Invalid input.");
                length = 0;
            }
        }
        return length;
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

    public void addIBook() {
        String title = this.setTitle();
        if (!title.equals("")) {
            boolean complete = askYesOrNo("Have you finished reading this book? [yes/NO]");
            String format = this.setFormat();
            switch (format) {
                case "book":
                    this.addBook(title, complete);
                    break;
                case "ebook":
                    this.addEbook(title, complete);
                    break;
                case "audiobook":
                    this.addAudiobook(title, complete);
                    break;
            }
        }
    }

    // --> Format: "To Kill A Mockingbird – You need to go from page 273 to 295 (of 530) today.
    public void printBookSuggestion(iBook book) {
        if (book.isComplete()) {
            System.out.printf("%s – This book is complete.", book.getTitle());
        } else {
            if (book.getFormat().equals("book")) {
                System.out.printf("%s – You need to go from page %d to %d (of %d) today",
                        book.getTitle(),
                        book.getPagesComplete(),
                        tracker.suggestion(book, nextDay),
                        book.getTotalPages()
                        );
            } else if (book.getFormat().equals("ebook")) {
                System.out.printf("%s – You need to go from %d%% to %d%% today.",
                        book.getTitle(),
                        book.percentComplete(),
                        tracker.suggestion(book, nextDay));
            } else if (book.getFormat().equals("audiobook")) {
                System.out.printf("%s – You need to go from %s to %s (of %s) today",
                        book.getTitle(),
                        Audiobook.secondsToFormattedTime(book.pagesComplete),
                        Audiobook.secondsToFormattedTime(tracker.suggestion(book, nextDay)),
                        Audiobook.secondsToFormattedTime(book.totalPages));
            }
        }
    }

    public void printAllBookSuggestions() {
        for (iBook book : books) {
            printBookSuggestion(book);
        }
    }

    private void addBook(String title, boolean complete) {
        int pages = scanIntWithMinimum("How many pages does the book have?", 1);
        int pagesComplete = complete ? pages : scanIntBetween("How many pages have you read so far?", 0, pages);
        Book newBook = new Book(title, pages, pagesComplete);
        this.books.add(newBook);
    }

    private void addEbook(String title, boolean complete) {
        int percentComplete = complete ? 100 : scanIntBetween("What percentage of the book have you completed?", 0, 100);
        Ebook newEbook = new Ebook(title, percentComplete);
        this.books.add(newEbook);
    }

    private void addAudiobook(String title, boolean complete) {
        int length = scanTime("How long is the audiobook?");
        int amountComplete = complete ? length : scanTime("How much of the audibook have you listened to?");
        Audiobook newAudiobook = new Audiobook(title, length, amountComplete);
        this.books.add(newAudiobook);
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

    public void updateBook(iBook book) {
        int pages = scanIntBetween("How many pages of the book have you read?", 0, book.getTotalPages());
        book.setPagesComplete(pages);
    }

    public void deleteBook(iBook book) {
        boolean sure = askYesOrNo("Are you sure you want to delete this book? This process is irreversible", false);
        if (sure) {
            books.remove(book);
        }
    }
}

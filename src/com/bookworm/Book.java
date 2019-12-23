package com.bookworm;

public class Book {
    private static final int BOOK = 0;
    private static final int EBOOK = 1;
    private static final int AUDIOBOOK = 2;

    private final String title;
    private int format = BOOK;
    private int pages;
    private int pagesComplete = 0;
    private boolean complete = false;


    public Book(String title, int format, int pagesComplete, int pages) {
        this.title = title;
        this.format = format;
        this.pagesComplete = pagesComplete;
        this.pages = pages;
    }

}

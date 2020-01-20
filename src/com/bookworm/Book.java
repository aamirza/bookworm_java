package com.bookworm;

public class Book extends iBook {

    public Book(String title, int totalPages) {
        this(title, totalPages, 0);
    }

    public Book(String title, int totalPages, int pagesComplete) {
        super(title, 0, totalPages, pagesComplete);
    }

    @Override
    public boolean isComplete() {
        return pagesComplete >= totalPages;
    }
}

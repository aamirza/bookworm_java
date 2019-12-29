package com.bookworm;

public class Book extends iBook {
    private final int totalPages;
    private int pagesComplete = 0;

    public Book(String title, int totalPages, int pagesComplete) {
        super(title);
        format = BOOK;
        this.totalPages = totalPages;
        this.setPagesComplete(pagesComplete);
        this.complete = this.isComplete();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPagesComplete() {
        return pagesComplete;
    }

    public void setPagesComplete(int pagesComplete) {
        this.pagesComplete = pagesComplete;
    }

    @Override
    public boolean isComplete() {
        return pagesComplete >= totalPages;
    }

    @Override
    public double percentComplete() {
        return (double)pagesComplete / (double) totalPages;
    }
}

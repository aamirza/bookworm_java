package com.bookworm;

public class Book extends iBook {
    private final int pages;
    private int pagesComplete;


    public Book(String title, int pages) {
        super(title);
        this.pages = pages;
        this.pagesComplete = 0;
    }

    public Book(String title, int pages, int pagesComplete) {
        super(title);
        this.pages = pages;
        this.pagesComplete = pagesComplete;
        this.complete = this.isComplete();
    }

    public int getPages() {
        return pages;
    }

    public int getPagesComplete() {
        return pagesComplete;
    }

    public void setPagesComplete(int pagesComplete) {
        this.pagesComplete = pagesComplete;
    }

    @Override
    public boolean isComplete() {
        return pagesComplete >= pages;
    }

    @Override
    public double percentComplete() {
        return (double)pagesComplete / (double)pages;
    }
}

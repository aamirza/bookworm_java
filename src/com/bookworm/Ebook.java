package com.bookworm;

public class Ebook extends iBook {
    private static final int totalPages = 100;
    private int percentComplete;

    public Ebook(String title, int percentComplete) {
        super(title);
        this.percentComplete = percentComplete;
    }

    @Override
    public boolean isComplete() {
        return percentComplete == 100;
    }

    @Override
    public double percentComplete() {
        return percentComplete;
    }
}

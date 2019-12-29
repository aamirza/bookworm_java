package com.bookworm;

public class Ebook extends iBook {
    private static final int totalPages = 100;

    private int percentComplete = 0;

    public Ebook(String title, int percentComplete) {
        super(title);
        format = EBOOK;
        this.setPercentComplete(percentComplete);
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(int percentComplete) {
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

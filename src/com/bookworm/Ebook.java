package com.bookworm;

public class Ebook extends iBook {

    public Ebook(String title, int percentComplete) {
        super(title, 1, 100, percentComplete);
    }

    public int getPercentComplete() {
        return pagesComplete;
    }

    public void setPercentComplete(int percentComplete) {
        this.setPagesComplete(percentComplete);
    }

    @Override
    public boolean isComplete() {
        return pagesComplete == 100;
    }

    @Override
    public double percentComplete() {
        return pagesComplete;
    }
}

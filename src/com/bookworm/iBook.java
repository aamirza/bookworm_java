package com.bookworm;

import java.util.HashMap;

public abstract class iBook {

    protected int BOOK = 0;
    protected int EBOOK = 1;
    protected int AUDIOBOOK = 2;

    private final String title;
    protected final int totalPages;
    protected int pagesComplete;
    protected final int format;
    protected boolean complete = false;

    public iBook(String title, int format, int totalPages) {
        this(title, format, totalPages, 0);
    }

    public iBook(String title, int format, int totalPages, int pagesComplete) {
        this.title = title;
        this.format = format;
        this.totalPages = totalPages;
        this.pagesComplete = pagesComplete;
    }

    public static HashMap<Integer, String> getFormats() {
        HashMap<Integer, String> formats = new HashMap<>();
        formats.put(0, "book");
        formats.put(1, "ebook");
        formats.put(2, "audiobook");
        return formats;
    }

    public String getTitle() {
        return title;
    }

    public String getFormat() {
        return getFormats().get(this.format);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPagesComplete() {
        return pagesComplete;
    }

    public void setPagesComplete(int pagesComplete) {
        if (pagesComplete < 0 && pagesComplete <= this.totalPages) {
            this.pagesComplete = pagesComplete;
        }
    }

    public double percentComplete() {
        return (double) (pagesComplete / totalPages);
    }

    public abstract boolean isComplete();
}

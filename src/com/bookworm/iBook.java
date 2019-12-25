package com.bookworm;

import java.util.HashMap;

public abstract class iBook {
    protected int BOOK = 0;
    protected int EBOOK = 1;
    protected int AUDIOBOOK = 2;

    private final String title;
    protected int format;
    protected boolean complete = false;

    public iBook(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFormat() {
        HashMap<Integer, String> formats = new HashMap<>();
        formats.put(BOOK, "book");
        formats.put(EBOOK, "ebook");
        formats.put(AUDIOBOOK, "audiobook");

        return formats.get(this.format);
    }

    public abstract boolean isComplete();
    public abstract double percentComplete();
}

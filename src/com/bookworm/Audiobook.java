package com.bookworm;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Audiobook extends iBook {
    public static final SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");
    public static final SimpleDateFormat minuteFormat = new SimpleDateFormat("mm:ss");


    Audiobook(String title, String length) throws ParseException {
        this(title, (int) (hourFormat.parse(length).getTime() / 1000));
    }

    Audiobook(String title, int length, String amountListened) throws ParseException {
        this(title, length, (int) (hourFormat.parse(amountListened).getTime() / 1000));
    }

    Audiobook(String title, String length, String amountListened) throws ParseException {
        this(title, length, (int) (hourFormat.parse(amountListened).getTime() / 1000));
    }

    Audiobook(String title, String length, int amountListened) throws ParseException {
        this(title, (int) (hourFormat.parse(length).getTime() / 1000), amountListened);
    }

    Audiobook(String title, int length) {
        this(title, length, 0);
    }

    Audiobook(String title, int length, int amountListened) {
        super(title, 2, length, amountListened);
    }

    public static String secondsToFormattedTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int minutes = (timeInSeconds - (hours*3600)) / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%d:%d:%d", hours, minutes, seconds);
    }

    public static int timeToSeconds(String formattedTime) throws ParseException {
        int length;
        try {
            length = (int) (hourFormat.parse(formattedTime).getTime() / 1000);
        } catch (ParseException e) {
            length = (int) (minuteFormat.parse(formattedTime).getTime() / 1000);
        }
        return length;
    }

    public String getAmountListened() {
        return secondsToFormattedTime(pagesComplete);
    }

    public void setAmountListened(String amountListened) throws ParseException {
        try {
            this.pagesComplete = (int) (hourFormat.parse(amountListened).getTime() / 1000);
        } catch (ParseException e) {
            this.pagesComplete = (int) (minuteFormat.parse(amountListened).getTime() / 1000);
        }
    }

    @Override
    public boolean isComplete() {
        return pagesComplete >= totalPages;
    }
}

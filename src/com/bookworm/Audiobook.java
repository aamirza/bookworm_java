package com.bookworm;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Audiobook extends iBook {
    private final int length;
    private int amountListened = 0;
    private SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");
    private SimpleDateFormat minuteFormat = new SimpleDateFormat("mm:ss");

    public Audiobook(String title, String length) throws ParseException {
        this(title, length, "0:00:00");
    }

    public Audiobook(String title, String length, int amountListened) throws ParseException {
        this(title, length, "0:00:00");
        this.amountListened = amountListened;
    }

    public Audiobook(String title, String length, String amountListened) throws ParseException {
        super(title);
        format = AUDIOBOOK;
        int length1;
        try {
            length1 = (int) (hourFormat.parse(length).getTime() / 1000);
        } catch (ParseException e) {
            length1 = (int) (minuteFormat.parse(length).getTime() / 1000);
        }
        this.length = length1;
        this.setAmountListened(amountListened);
    }

    private static String secondsToFormattedTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int minutes = (timeInSeconds - (hours*3600)) / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%d:%d:%d", hours, minutes, seconds);
    }

    public int getAmountListened() {
        return amountListened;
    }

    public String getFormattedAmountListened() {
       return secondsToFormattedTime(amountListened);
    }

    public void setAmountListened(String amountListened) throws ParseException {
        try {
            this.amountListened = (int) (hourFormat.parse(amountListened).getTime() / 1000);
        } catch (ParseException e) {
            this.amountListened = (int) (minuteFormat.parse(amountListened).getTime() / 1000);
        }
    }

    public void setAmountListened(int amountListened) {
        this.amountListened = amountListened;
    }

    public int getLength() {
        return length;
    }

    public String getFormattedLength() {
        return secondsToFormattedTime(length);
    }

    @Override
    public boolean isComplete() {
        return amountListened >= length;
    }

    @Override
    public double percentComplete() {
        return (double) amountListened / (double) length;
    }
}

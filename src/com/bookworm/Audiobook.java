package com.bookworm;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Audiobook extends iBook {
    private final int length;
    private int amountListened;
    private SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");
    private SimpleDateFormat minuteFormat = new SimpleDateFormat("mm:ss");

    public Audiobook(String title, String length) throws ParseException {
        super(title);
        int length1;
        try {
            length1 = (int) (hourFormat.parse(length).getTime() / 1000);
        } catch (ParseException e) {
            length1 = (int) (minuteFormat.parse(length).getTime() / 1000);
        }
        this.length = length1;
        this.amountListened = 0;
    }

    public int getAmountListened() {
        return amountListened;
    }

    public void setAmountListened(String amountListened) throws ParseException {
        try {
            this.amountListened = (int) (hourFormat.parse(amountListened).getTime() / 1000);
        } catch (ParseException e) {
            this.amountListened = (int) (minuteFormat.parse(amountListened).getTime() / 1000);
        }
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

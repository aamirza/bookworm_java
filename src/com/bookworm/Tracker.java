package com.bookworm;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tracker {
    private int goal;
    private ArrayList<iBook> books = new ArrayList<iBook>();
    private static LocalDate startDate = LocalDate.ofYearDay(LocalDate.now().getYear(), 1);
    private static LocalDate endDate = LocalDate.ofYearDay(LocalDate.now().getYear()+1, 1).minusDays(1);

    private long daysInYear = ChronoUnit.DAYS.between(startDate, endDate) + 1;


    Tracker(int goal) {
        this.goal = goal;
    }

    Tracker(int goal, ArrayList<iBook> books) {
        this.goal = goal;
        this.books = books;
    }

    private static long daysSinceStart() {
        return ChronoUnit.DAYS.between(startDate, LocalDate.now());
    }

    public double minimumProgress() {
        return (double) (daysSinceStart() / daysInYear);
    }

    private double nextDayProgress() {
        int daysComplete = (int) (getProgress() / daysInYear);
        return (double) (daysComplete + 1) / daysInYear;
    }

    private double getProgress() {
        double totalProgress = 0;
        for (iBook book : books) {
            totalProgress += book.percentComplete();
        }
        return totalProgress;
    }

    // --> Format: "To Kill A Mockingbird – You need to go from page 273 to 295 (of 530) today.
    public int suggestion(iBook book, boolean nextDay) {
        double minimumProgress = nextDay ? nextDayProgress() : minimumProgress();
        double neededProgress = minimumProgress - getProgress();
        double suggestedBookProgress = book.percentComplete() + neededProgress;
        suggestedBookProgress = suggestedBookProgress > 1 ? 1 : suggestedBookProgress;
        int suggestedPages = (int) (book.getTotalPages() * suggestedBookProgress);
        return suggestedPages;
    }
}

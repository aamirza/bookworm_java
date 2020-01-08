package com.bookworm;

public class Prompt {
    private iBook[] books;
    private int goal = 0;

    public void setGoal() {
        if (this.goal <= 0) {
            System.out.printf("You don't have a goal set. ");
        }
        System.out.printf("How many books do you want to read this year? >>> ");
        
    }
}

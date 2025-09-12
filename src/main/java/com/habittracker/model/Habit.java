package com.habittracker.model;

public class Habit {
    private int id;
    private String username;
    private String habitName;
    private int goal;
    private int progress;

    public Habit(String username, String habitName, int goal) {
        this.username = username;
        this.habitName = habitName;
        this.goal = goal;
        this.progress = 0;
    }

    public Habit(int id, String username, String habitName, int goal, int progress) {
        this.id = id;
        this.username = username;
        this.habitName = habitName;
        this.goal = goal;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHabitName() {
        return habitName;
    }

    public int getGoal() {
        return goal;
    }

    public int getProgress() {
        return progress;
    }
}

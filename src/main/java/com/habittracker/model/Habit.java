package com.habittracker.model;

public class Habit {
    private int id;
    private String username;
    private String habitName;
    private int goal;
    private int progress;
    private boolean completed;


    public Habit(String username, String habitName, int goal) {
        this.username = username;
        this.habitName = habitName;
        this.goal = goal;
        this.progress = 0;
        this.completed = false;
    }

 
    public Habit(int id, String username, String habitName, int goal, int progress, boolean completed) {
        this.id = id;
        this.username = username;
        this.habitName = habitName;
        this.goal = goal;
        this.progress = progress;
        this.completed = completed;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getHabitName() { return habitName; }
    public int getGoal() { return goal; }
    public int getProgress() { return progress; }
    public boolean isCompleted() { return completed; }
}

package com.habittracker.model;

import java.sql.Timestamp;

public class Notification {

    private int id;
    private String username;
    private String message;
    private Timestamp sentTime;
    private String status;

    public Notification(int id, String username, String message, Timestamp sentTime, String status) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.sentTime = sentTime;
        this.status = status;
    }

    public int getId() { return id; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
    public Timestamp getSentTime() { return sentTime; }
}

package com.example.dmrcconnect;

public class Announcement {

    private String id;
    private String title;
    private String description;
    private String line;
    private String timestamp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Announcement(String id, String title, String description, String line, String timestamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.line = line;
        this.timestamp = timestamp;
    }
}

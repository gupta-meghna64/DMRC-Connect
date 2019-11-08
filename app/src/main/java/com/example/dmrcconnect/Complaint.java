package com.example.dmrcconnect;

public class Complaint {

    String id;
    String title;
    String status;

    public String getId() {
        return id;
    }

    public Complaint(String id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.example.dmrcconnect;

public class CategoryDetails {

    private String title;
    private String description;
    private int type;

    public CategoryDetails(String title, String description) {
        this.title = title;
        this.description = description;
        this.type = 0;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

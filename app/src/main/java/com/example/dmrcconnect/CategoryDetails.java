package com.example.dmrcconnect;

public class CategoryDetails {

    private String title;
    private String description;
    private String type;
    private String id;

    public CategoryDetails(String title, String description, String type, String id) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

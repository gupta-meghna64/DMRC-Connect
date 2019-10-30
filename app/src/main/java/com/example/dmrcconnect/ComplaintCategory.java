package com.example.dmrcconnect;

public class ComplaintCategory {

    private String title;
    private String description;
    private String unique_id;

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

    public ComplaintCategory(String title, String description, String unique_id) {
        this.title = title;
        this.description = description;
        this.unique_id = unique_id;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }
}

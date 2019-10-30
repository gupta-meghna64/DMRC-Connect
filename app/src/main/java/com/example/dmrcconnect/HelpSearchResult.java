package com.example.dmrcconnect;

import android.content.Context;

public class HelpSearchResult {

    private int image;
    private String name;

    public int getImage() {
        return image;
    }

    public HelpSearchResult(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

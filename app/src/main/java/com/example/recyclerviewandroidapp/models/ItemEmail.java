package com.example.recyclerviewandroidapp.models;

import java.util.Random;

public class ItemEmail {
    private String name;
    private String content;
    private String time;
    private boolean isSelected;
    private int color;

    public int getColor() {
        return color;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ItemEmail(String name, String content) {
        this.name = name;
        this.content = content;
        this.isSelected = false;

        Random random = new Random();
        color = random.nextInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

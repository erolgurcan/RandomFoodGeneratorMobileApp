package com.example.randomdietgeneratorapp;

public class Item {

    private String imageUrl;
    private String title;
    private double calorie;

    public Item(String imageUrl, String tags, int likes) {
        
        this.imageUrl = imageUrl;
        this.title = tags;
        this.calorie = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public double getCalorie() {
        return calorie;
    }

}

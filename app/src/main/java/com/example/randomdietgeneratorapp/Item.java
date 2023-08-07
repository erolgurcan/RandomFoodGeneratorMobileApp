package com.example.randomdietgeneratorapp;

public class Item {

    private String imageUrl;
    private String title;

    public Item(String imageUrl, String title) {
        
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

}

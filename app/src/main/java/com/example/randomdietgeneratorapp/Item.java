package com.example.randomdietgeneratorapp;

public class Item {

    private String imageUrl;
    private String title;
    private String calories;

    private Integer id;

    public Item(String imageUrl, String title, String calories, Integer id) {
        
        this.imageUrl = imageUrl;
        this.title = title;
        this.calories = calories;
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCalories() {return calories;}

    public Integer getId() {return id;}
}

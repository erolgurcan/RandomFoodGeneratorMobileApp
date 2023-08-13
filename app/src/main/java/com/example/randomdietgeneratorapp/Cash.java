package com.example.randomdietgeneratorapp;

import android.app.Application;

public class Cash extends Application {

    private  String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

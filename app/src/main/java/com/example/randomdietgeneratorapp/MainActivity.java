package com.example.randomdietgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnRegister;

    Button btnFetchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnFetchData = findViewById(R.id.btnFetchData);

        btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

//

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitiateLoginActivity();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitiateLRegisterActivity();
            }
        });



    }
    private void InitiateLoginActivity(){
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void  InitiateLRegisterActivity(){
        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }




}
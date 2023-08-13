package com.example.randomdietgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randomdietgeneratorapp.database.FeedReaderDbHelper;

import java.io.FileOutputStream;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    CardView loginCard;
    Button btnLogin;
    TextView email;
    TextView password;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);
        context = getApplication();
        setContentView(R.layout.activity_login);
        loginCard = findViewById(R.id.loginCard);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);

        loginCardAnimation(loginCard);

        ImageView imageView = findViewById(R.id.loginLogo);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        imageView.startAnimation(fadeInAnimation);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();

                // Call the method to check if the user exists in the database
                boolean userExists = dbHelper.checkUserCredentials(userEmail, userPassword);

                if (userExists) {
                    // User exists, proceed to the next activity or perform the desired action


                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userEmail", userEmail);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                    Intent fetchIntent = new Intent(LoginActivity.this, FoodSelectionActivity.class);

                    // Add user information as extras to the Intent
                    fetchIntent.putExtra("user_email", userEmail);
                    // Add any other user information you want to pass to the FetchData activity
                    Cash userCash = new Cash();

                    startActivity(fetchIntent);
                } else {
                    // User does not exist or the provided credentials are incorrect
                    Toast.makeText(LoginActivity.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginCardAnimation (CardView loginCard){

        loginCard.setTranslationY(200f); // Initial position below the desired final position
        loginCard.animate()
                .translationY(0f) // Final position
                .setDuration(1000) // Duration in milliseconds
                .start();
    }
}
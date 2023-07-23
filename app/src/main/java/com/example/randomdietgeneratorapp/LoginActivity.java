package com.example.randomdietgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randomdietgeneratorapp.databinding.ActivityLoginBinding;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    CardView loginCard;
    Button btnLogin;
    TextView email;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);

        setContentView(R.layout.activity_login);
        loginCard = findViewById(R.id.loginCard);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);

        loginCardAnimation(loginCard);

        ImageView imageView = findViewById(R.id.loginLogo);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        imageView.startAnimation(fadeInAnimation);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(email.getText().toString().equals("") ||  password.getText().toString().equals("")){
                   Toast.makeText(LoginActivity.this, "Email or Password field empty", Toast.LENGTH_SHORT).show();
                }else {

//                   if(!databaseHelper.checkEmail(email.getText().toString() ))   {
//                       Toast.makeText(LoginActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
//                   }
//
//                   if(!databaseHelper.checkEmailPassword(email.getText().toString(), password.getText().toString())){
//                       Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
//                   }
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
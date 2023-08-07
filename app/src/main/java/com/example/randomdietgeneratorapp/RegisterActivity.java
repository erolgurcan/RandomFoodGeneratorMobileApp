package com.example.randomdietgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.randomdietgeneratorapp.database.FeedReaderDbHelper;

public class RegisterActivity extends AppCompatActivity {
   private Button registerBtn;
   private EditText registerUserName;
   private EditText registerUserEmail;
   private EditText registerPassword1;
   private EditText registerPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        registerBtn = findViewById(R.id.btnRegister);
        registerUserName = findViewById(R.id.registerUserName);
        registerUserEmail = findViewById(R.id.registerEmail);
        registerPassword1 = findViewById(R.id.registerPassword1);
        registerPassword2 = findViewById(R.id.registerPassword2);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = registerUserName.getText().toString().trim();
                String email = registerUserEmail.getText().toString().trim();
                String password1 = registerPassword1.getText().toString().trim();
                String password2 = registerPassword1.getText().toString().trim();

                if(password1.equals(password2)){
                    ContentValues values = new ContentValues();
                    values.put("name", username);
                    values.put("email", email);
                    values.put("password", password1);

                    long newRowId = db.insert("user", null, values);

                    if (newRowId != -1) {
                        // Registration successful
                        Toast.makeText(RegisterActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                        // You can add further logic here, such as navigating to another activity, etc.
                    } else {
                        // Registration failed
                        Toast.makeText(RegisterActivity.this, "Failed to register user.", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this, "Passwords are not same", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
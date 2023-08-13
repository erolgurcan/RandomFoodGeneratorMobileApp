package com.example.randomdietgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.randomdietgeneratorapp.database.FeedReaderDbHelper;

import java.util.EventListener;

public class FoodSelectionActivity extends AppCompatActivity {

    private TextView userText;

    private Spinner cuisineType;
    private EditText calories;
    private Button submit;
    private  String selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);

        userText = findViewById(R.id.textUser);
        calories = findViewById(R.id.calorieEditText);
        submit = findViewById(R.id.btnFoodSubmit);
        Spinner spinner = (Spinner) findViewById(R.id.cuisineTypeSpinner);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("userEmail", "");
        String userName = dbHelper.getUserNameByEmail(userEmail);


        userText.setText(userName);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                // Do something with the selected item (e.g., store it or process it)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fetchFoodActivity = new Intent(FoodSelectionActivity.this, FetchData.class);
                fetchFoodActivity.putExtra("selectedCuisine", selectedItem);
                fetchFoodActivity.putExtra("maxCalories", calories.getText().toString().trim());
                startActivity(fetchFoodActivity);
            }
        });

    }
}
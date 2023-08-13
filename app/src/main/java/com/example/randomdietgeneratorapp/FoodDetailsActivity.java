package com.example.randomdietgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.randomdietgeneratorapp.database.FeedReaderDbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FoodDetailsActivity extends AppCompatActivity {

    private ImageView foodDetailsImage;
    private TextView foodDetailsHeader;
    private TextView textHealthScore;
    private TextView foodDetailsVegeterian;
    private TextView foodDetailsCheap;
    private TextView foodDetailsDairyFree;
    private TextView foodDetailsInnredients;

    private Button saveBtn;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        foodDetailsImage = findViewById(R.id.foodDetailsImage);
        foodDetailsHeader = findViewById(R.id.foodDetailsHeader);
        textHealthScore = findViewById(R.id.textHealthScore);
        foodDetailsVegeterian = findViewById(R.id.foodDetailsVegeterian);
        foodDetailsCheap = findViewById(R.id.FoodDetailsCheap);
        foodDetailsDairyFree = findViewById(R.id.foodDetailsDairyFree);
        foodDetailsInnredients = findViewById(R.id.foodDetailsInnredients);
        saveBtn = findViewById(R.id.btnFoodSave);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Intent intent = getIntent();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String userEmail = sharedPreferences.getString("userEmail", null);

        String title = intent.getStringExtra("title");
        int healthScore = intent.getIntExtra("healthScore", 0);
        boolean isVegeterian = intent.getBooleanExtra("isVegeterian", false);
        boolean isCheap = intent.getBooleanExtra("isCheap", false);
        boolean isDairyFree = intent.getBooleanExtra("isDairyFree", false);
        String ingredients = intent.getStringExtra("ingredients");
        int id = intent.getIntExtra("id", 0);

        foodDetailsHeader.setText(title.toString().trim());

        String imageUrl = intent.getStringExtra("image");

        Glide.with(this)
                .load(imageUrl)
                .into(foodDetailsImage);

        fetchDetails(id);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put("userEmail", userEmail);
                values.put("foodId", id);

                try{
                    long newRowId = db.insert("userFood", null, values );

                    if (newRowId != -1){
                        Toast.makeText(FoodDetailsActivity.this, "Food item added to user's collection", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(FoodDetailsActivity.this, "Failed to add food item", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                        //Log.e("message", e.toString());
                }



            }
        });


    }

    private void fetchDetails(int id) {
        String url = "https://api.spoonacular.com/recipes/" +  id  + "/information?includeNutrition=false&apiKey=41a942b9d8c9466186d2006ed5e66bd6";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject jsonArray = response;
                StringBuilder ingredientsStringBuilder = new StringBuilder();

                Boolean isVegeterian = jsonArray.getBoolean("vegetarian");
                Boolean isCheap = jsonArray.getBoolean("cheap");
                Boolean isdairyFree = jsonArray.getBoolean("dairyFree");
                String healthScore = jsonArray.getString("healthScore");

                textHealthScore.setText(healthScore.toString().trim());
                foodDetailsCheap.setText(isVegeterian.toString().trim());
                foodDetailsVegeterian.setText(isVegeterian.toString().trim());
                foodDetailsCheap.setText(isCheap.toString().trim());
                foodDetailsDairyFree.setText(isdairyFree.toString().trim());

                JSONArray ingredientsArray = response.getJSONArray("extendedIngredients");

                for (int i = 0; i < ingredientsArray.length(); i++) {
                    JSONObject ingredientObject = ingredientsArray.getJSONObject(i);
                    String ingredientName = ingredientObject.getString("name");

                    if (i > 0) {
                        ingredientsStringBuilder.append(", ");
                    }
                    ingredientsStringBuilder.append(ingredientName);
                }

                String allIngredientNames = ingredientsStringBuilder.toString();
                foodDetailsInnredients.setText(allIngredientNames);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
        });
        requestQueue.add(jsonObjectRequest);
    }
}
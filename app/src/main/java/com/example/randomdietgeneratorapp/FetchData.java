package com.example.randomdietgeneratorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FetchData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Item> list;

    private String selectedCuisine;

    private String maxCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch_data);


        Intent intent = getIntent();
        if (intent !=null){
            this.selectedCuisine = intent.getStringExtra("selectedCuisine");
            this.maxCalories = intent.getStringExtra("maxCalories");
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        list = new ArrayList<>();

        fetchFood();
    }

    private void fetchFood() {

        String url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=41a942b9d8c9466186d2006ed5e66bd6&cuisine=" + selectedCuisine +"&maxCalories=" + maxCalories;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imageUrl = jsonObject.getString("image");
                        String title = jsonObject.getString("title");
                        Integer id = jsonObject.getInt("id");

                        JSONObject nutrients = (JSONObject) jsonObject.getJSONObject("nutrition").getJSONArray("nutrients").get(0);
                        String calories = nutrients.getString("amount");

                        Item item = new Item(imageUrl, title, calories, id);
                        list.add(item);
                    }

                    RecyclerAdapter adapter = new RecyclerAdapter(FetchData.this, list);
                    recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Toast.makeText(FetchData.this, e.getMessage(), Toast.LENGTH_LONG);
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    };

    }



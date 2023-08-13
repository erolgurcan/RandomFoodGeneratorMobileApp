package com.example.randomdietgeneratorapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<Item> itemList;
    View view;


    public RecyclerAdapter(Context context, List<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.setImageView(item.getImageUrl());
        holder.setTextView(item.getTitle());
        holder.setCalories(item.getCalories());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent foodDetailsActivity = new Intent(context, FoodDetailsActivity.class);
                foodDetailsActivity.putExtra("id", item.getId());
                foodDetailsActivity.putExtra("calories", item.getCalories());
                foodDetailsActivity.putExtra("title", item.getTitle());
                foodDetailsActivity.putExtra("image", item.getImageUrl());

                context.startActivity(foodDetailsActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView calories;

        Integer id;
        View view;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            this.cardView = itemView.findViewById(R.id.loginCard);
        }

        public void setImageView(String url) {
            this.imageView = view.findViewById(R.id.foodView);
            Glide.with(context).load(url).into(imageView);
        }

        public void setTextView(String textView) {
            this.textView = view.findViewById(R.id.foodHeader1);
            this.textView.setText(textView);
        }

        public void setCalories(String calories){
            this.calories = view.findViewById(R.id.foodHeader3);
            this.calories.setText(calories);
        }

    }
}

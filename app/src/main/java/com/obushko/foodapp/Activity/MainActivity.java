package com.obushko.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.obushko.foodapp.Adapter.FoodListAdapter;
import com.obushko.foodapp.Domain.FoodDomain;
import com.obushko.foodapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FoodListAdapter foodListAdapter;
    private RecyclerView recyclerViewFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<FoodDomain> items = new ArrayList<>();
        items.add(new FoodDomain("Cheese Burger", "Satisfy your cravings with our juicy Cheese Burger. \n"+
                "Made with 100% Angus beef patty and topped with\n"+
                "melted cheddar cheese, fresh lettuce, tomato, and\n"+
                "our secret sauce, this classic burger will leave you\n"+
                "wanting more. Served with crispy fries and a drink,\n"+
                "it's the perfect meal for any occasion.", "fast_1", 15, 20, 120, 4));
        items.add(new FoodDomain("Pizza Peperoni", "Get a taste of Italy with our delicious Pepperoni Pizza. Made with freshly rolled dough,zesty tomato sauce, mozzarella cheese and topped with spicy pepperoni slices, this pizza is sure to be a crowd-pleaser.", "fast_2", 10, 25, 200, 5));
        items.add(new FoodDomain("Vegetable Pizza", "Looking for a healthier option? Iry our Vegetable Pizza, made with a variety of fresh veggies such as bell peppers, onions, mushrooms, olives, and tomatoes. Topped with mozzarella cheese and a tangy tomato sauce, this pizza is full of flavor and goodness.", "fast_3", 13, 30, 100, 4.5));

        recyclerViewFood = findViewById(R.id.recyclerView1);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        foodListAdapter = new FoodListAdapter(items);
        recyclerViewFood.setAdapter(foodListAdapter);
    }
}
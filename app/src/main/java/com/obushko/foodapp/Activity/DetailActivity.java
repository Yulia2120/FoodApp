package com.obushko.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.obushko.foodapp.Domain.FoodDomain;
import com.obushko.foodapp.Helper.ManagmentCart;
import com.obushko.foodapp.R;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView plusCartBtn, minusCartBtn, numItemTxt, descriptionTxt, energyTxt, ratingTxt, timeTxt,
    priceTxt, titleTxt;
    private ImageView picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        
        managmentCart = new ManagmentCart(this);
        
        initView();
        getBundle();
    }

    private void getBundle() {
        object=(FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        priceTxt.setText("$"+ object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numItemTxt.setText("" + numberOrder);
        energyTxt.setText(object.getEnergy() + " Cal");
        ratingTxt.setText(object.getScore() + "");
        timeTxt.setText(object.getTime() + " min");
        addToCartBtn.setText("Add to cart - $"+Math.round(numberOrder*object.getPrice()));

        plusCartBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            numItemTxt.setText(""+ numberOrder);
            addToCartBtn.setText("Add to cart - $"+Math.round(numberOrder*object.getPrice()));
        });
        minusCartBtn.setOnClickListener(v -> {
            numberOrder = numberOrder - 1;
            numItemTxt.setText(""+ numberOrder);
            addToCartBtn.setText("Add to cart - $"+Math.round(numberOrder*object.getPrice()));
        });

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managmentCart.insertFood(object);
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
         plusCartBtn= findViewById(R.id.plusCartBtn);
         minusCartBtn= findViewById(R.id.minusCartBtn);
         numItemTxt= findViewById(R.id.numItemTxt);
         descriptionTxt= findViewById(R.id.descriptionTxt);
         energyTxt= findViewById(R.id.energyTxt);
         ratingTxt= findViewById(R.id.ratingTxt);
         timeTxt= findViewById(R.id.timeTxt);
         priceTxt= findViewById(R.id.priceTxt);
         titleTxt= findViewById(R.id.titleTxt);
         picFood = findViewById(R.id.foodPic);
    }
}
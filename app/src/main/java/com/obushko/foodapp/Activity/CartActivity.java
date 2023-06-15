package com.obushko.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.obushko.foodapp.Adapter.CartListAdapter;
import com.obushko.foodapp.Helper.ChangeNumberItemsListener;
import com.obushko.foodapp.Helper.ManagmentCart;
import com.obushko.foodapp.R;

public class CartActivity extends AppCompatActivity {

    private CartListAdapter cartListAdapter;
    private RecyclerView recyclerView;
    private ManagmentCart managmentCart;
    private TextView totalFeeTxt, deliveryTxt, taxTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managmentCart = new ManagmentCart(this);
        initView();
        initList();
        calculateCart();
        setVariable();
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        cartListAdapter = new CartListAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });
        recyclerView.setAdapter(cartListAdapter);

        if(managmentCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void calculateCart(){
        double percentTax = 0.02; //tax price
        double delivery = 10;
        tax = Math.round((managmentCart.getTotalFee()*percentTax*100.0))/100.0;

        double total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100.0)/100.0;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100.0)/100.0;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        taxTxt = findViewById(R.id.taxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        recyclerView = findViewById(R.id.recyclerViewCart);
        scrollView = findViewById(R.id.scrollView3);
        backBtn = findViewById(R.id.backBtn);
    }
}
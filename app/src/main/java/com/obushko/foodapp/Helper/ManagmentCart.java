package com.obushko.foodapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.obushko.foodapp.Domain.FoodDomain;

import java.util.ArrayList;

public class ManagmentCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; i<listFood.size(); i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }
    public void minusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemListener ){
        if(listFood.get(position).getNumberInCart() == 1){
            listFood.remove(position);
        }else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();

    }
    public void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemListener ){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();
    }

    //fee - комиссия
    public Double getTotalFee(){
        ArrayList<FoodDomain> listFood = getListCart();
        double fee = 0;
        for (int i = 0; i < listFood.size(); i++){
            fee = fee+(listFood.get(i).getPrice()*listFood.get(i).getNumberInCart());
        }
        return fee;
    }
}

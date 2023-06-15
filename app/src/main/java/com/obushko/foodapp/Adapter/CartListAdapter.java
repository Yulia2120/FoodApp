package com.obushko.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.obushko.foodapp.Domain.FoodDomain;
import com.obushko.foodapp.Helper.ChangeNumberItemsListener;
import com.obushko.foodapp.Helper.ManagmentCart;
import com.obushko.foodapp.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    ArrayList<FoodDomain> listFoodSelected;
    private ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listFoodSelected = listFoodSelected;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_cart, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("$"+listFoodSelected.get(position).getPrice());
        holder.totalEachItem.setText("$"+Math.round(listFoodSelected.get(position).getNumberInCart()*listFoodSelected.get(position).getPrice()));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                listFoodSelected.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.picImg);

        holder.plusItem.setOnClickListener(v -> managmentCart.plusNumberFood(listFoodSelected, position, ()->{
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.minusItem.setOnClickListener(v -> managmentCart.minusNumberFood(listFoodSelected,position, () ->{
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView picImg;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCartTxt);
            picImg = itemView.findViewById(R.id.picImg);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}

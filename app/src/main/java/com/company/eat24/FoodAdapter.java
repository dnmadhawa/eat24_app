package com.company.eat24;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;



public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.DFoodHolder> {

    Context context;
    ArrayList<Food> list;
    int row_index;

    public FoodAdapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public DFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.foodlist, parent, false);
        return new DFoodHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DFoodHolder holder, @SuppressLint("RecyclerView") int position) {
        Food food = list.get(position);
        holder.fname.setText(food.getName());
        holder.fprice.setText(food.getPrize());
        Glide.with(holder.fimg.getContext())
                .load(food.getFurl())
                .into(holder.fimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
                FoodMenu.fname = food.getName();
                FoodMenu.fprice = food.getPrize();
                holder.itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
        });
        if (row_index != position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DFoodHolder extends RecyclerView.ViewHolder {

        TextView fname;
        TextView fprice;
        ImageView fimg;

        public DFoodHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.dfood_name);
            fprice = itemView.findViewById(R.id.dfood_price);
            fimg = itemView.findViewById(R.id.dfood_img);
        }
    }
}


package com.company.eat24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KitchenAdapter extends RecyclerView.Adapter<KitchenAdapter.viewHolder>{

    ArrayList<KitchenModel> list;


    public KitchenAdapter(Fragment_kitchen fragment_kitchen, ArrayList<KitchenModel> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_kitchen,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

         KitchenModel kitchenModel = list.get(position);
         holder.dishImage.setImageResource(kitchenModel.getDishImage());
         holder.orderNumber.setText(kitchenModel.getOrderNumber());
         holder.itemName.setText(kitchenModel.getItemName());
         holder.itemQuantity.setText(kitchenModel.getItemQuantity());
         holder.status.setText(kitchenModel.getStatus());

        System.out.println(position);
        System.out.println(list.size());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{

     ImageView dishImage;
     TextView orderNumber,itemQuantity,itemName,status;


        public viewHolder(@NonNull View itemView){
            super(itemView);
            dishImage = itemView.findViewById(R.id.dish);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            itemName = itemView.findViewById(R.id.orderItem);
            itemQuantity = itemView.findViewById(R.id.itemquantity);
            status = itemView.findViewById(R.id.btnpreparing);


        }
    }
}

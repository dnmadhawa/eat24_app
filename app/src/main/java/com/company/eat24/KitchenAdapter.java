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

    ArrayList<KitchenModel> dataholder;

    public KitchenAdapter(ArrayList<KitchenModel> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_kitchen,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


         holder.dishImage.setImageResource(dataholder.get(position).getDishImage());
         holder.tableNumber.setText(dataholder.get(position).getTableNumber());
         holder.orderNumber.setText(dataholder.get(position).getOrderNumber());
         holder.itemName.setText(dataholder.get(position).getItemName());
         holder.itemQuantity.setText(dataholder.get(position).getItemQuantity());
         holder.status.setText(dataholder.get(position).getStatus());





    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

     ImageView dishImage;
     TextView orderNumber,tableNumber,itemQuantity,itemName,status;


        public viewHolder(@NonNull View itemView){
            super(itemView);
            dishImage = itemView.findViewById(R.id.dish);
            tableNumber = itemView.findViewById(R.id.tableNumber);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            itemName = itemView.findViewById(R.id.orderItem);
            status = itemView.findViewById(R.id.btnpreparing);
            status = itemView.findViewById(R.id.btnprepared);


        }
    }
}

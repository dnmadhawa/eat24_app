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

    ArrayList<KitchenModel>list;
    Context context;

    public KitchenAdapter(ArrayList<KitchenModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_kitchen,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final KitchenModel model = list.get(position);
        holder.dishImage.setImageResource(model.getDishImage());
        holder.tableNumber.setText(model.getTableNumber());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.itemName.setText(model.getItemName());
        holder.itemQuantity.setText(model.getItemQuantity());
        holder.status.setText(model.getStatus());




    }

    @Override
    public int getItemCount() {
        return list.size();
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

package com.company.eat24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class KitchenAdapter extends RecyclerView.Adapter<KitchenAdapter.viewHolder>{

    ArrayList<KitchenModel> dataholder;

    public KitchenAdapter(FirebaseRecyclerOptions<KitchenModel> dataholder) {
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

         KitchenModel kitchenModel = dataholder.get(position);
         holder.dishImage.setImageResource(kitchenModel.getDishImage());
         holder.tableNumber.setText(kitchenModel.getTableNumber());
         holder.orderNumber.setText(kitchenModel.getOrderNumber());
         holder.itemName.setText(kitchenModel.getItemName());
         holder.itemQuantity.setText(kitchenModel.getItemQuantity());
         holder.status.setText(kitchenModel.getStatus());

        System.out.println(position);
        System.out.println(dataholder.size());




    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public void startListening() {
    }

    public void stopListening() {
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
            itemQuantity = itemView.findViewById(R.id.itemquantity);
            status = itemView.findViewById(R.id.btnprepared);


        }
    }
}

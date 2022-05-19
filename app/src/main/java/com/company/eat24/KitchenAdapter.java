package com.company.eat24;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class KitchenAdapter extends RecyclerView.Adapter<KitchenAdapter.viewHolder>{

    ArrayList<OrderModel> list;


    public KitchenAdapter(Fragment_kitchen fragment_kitchen, ArrayList<OrderModel> list) {
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

         OrderModel kitchenModel = list.get(position);

         holder.orderNumber.setText(kitchenModel.getOrderID());
         holder.itemName.setText(kitchenModel.getItem());
         holder.itemQuantity.setText(kitchenModel.getQuantity());
         holder.status.setText(kitchenModel.getStatus());
         holder.status.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

              holder.status.setText("Prepared");

             }
         });

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
            orderNumber = itemView.findViewById(R.id.orderNumber);
            itemName = itemView.findViewById(R.id.orderItem);
            itemQuantity = itemView.findViewById(R.id.itemquantity);
            status = itemView.findViewById(R.id.btnpreparing);


        }
    }
}

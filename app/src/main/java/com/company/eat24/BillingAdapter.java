package com.company.eat24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BillingAdapter extends RecyclerView.Adapter<BillingAdapter.viewHolder>{

    ArrayList<OrderModel> list;


    public BillingAdapter(ArrayList<OrderModel> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bilingorderitem,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

         OrderModel orderModel = list.get(position);
         holder.itemprice.setText(orderModel.getPrice());
         holder.itemName.setText(orderModel.getItem());
         holder.itemQuantity.setText(orderModel.getQuantity());
         holder.status.setText(orderModel.getStatus());





    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{

     TextView itemprice,itemQuantity,itemName,status;


        public viewHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.bo_name);
            itemprice = itemView.findViewById(R.id.bo_price);
            itemQuantity = itemView.findViewById(R.id.bo_qty);
            status = itemView.findViewById(R.id.bo_status);


        }
    }
}

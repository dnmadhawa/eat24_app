package com.company.eat24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SingleBillingAdapter extends RecyclerView.Adapter<SingleBillingAdapter.viewHolder>{

    ArrayList<OrderModel> list;


    public SingleBillingAdapter(ArrayList<OrderModel> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_billing_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

         OrderModel orderModel = list.get(position);
         holder.itemprice.setText(orderModel.getPrice());
         holder.itemName.setText(orderModel.getItem());
         holder.itemQuantity.setText(orderModel.getQuantity());





    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{

     TextView itemprice,itemQuantity,itemName;


        public viewHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.sb_name);
            itemprice = itemView.findViewById(R.id.sb_price);
            itemQuantity = itemView.findViewById(R.id.sb_qty);


        }
    }
}

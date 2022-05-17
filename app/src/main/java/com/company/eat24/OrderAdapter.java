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


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder>{

    ArrayList<OrderModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_orders,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        OrderModel orderModel = list.get(position);

        holder.orderNum.setText(orderModel.getOrderID());
        holder.item.setText(orderModel.getItem());
        holder.itemQuant.setText(orderModel.getQuantity());
        holder.orderStatus.setText(orderModel.getStatus());

        System.out.println(position);
        System.out.println(list.size());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{


        TextView orderNum,itemQuant,item,orderStatus;


        public viewHolder(@NonNull View itemView){
            super(itemView);

            orderNum = itemView.findViewById(R.id.ordernum);
            item = itemView.findViewById(R.id.items);
            itemQuant = itemView.findViewById(R.id.quant);
            orderStatus = itemView.findViewById(R.id.orderstatus);


        }
    }
}

package com.company.eat24.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.eat24.activities.EditDeliveryOrder;
import com.company.eat24.R;
import com.company.eat24.models.Delivery;

import java.util.ArrayList;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    Context context;
    ArrayList<Delivery> list;

    public DeliveryAdapter(Context context, ArrayList<Delivery> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DeliveryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Delivery delivery = list.get(position);
        holder.v_did.setText("#00"+delivery.getD_id());
        holder.v_tamount.setText(delivery.getTotal_amount());
        holder.v_status.setText(delivery.getDelivery_status());
        holder.v_datatime.setText(delivery.getDatetime());
        switch (delivery.getDelivery_status()){
            case "Not Delivered":
                holder.v_status.getBackground().setTint(Color.parseColor("#E47575"));
                break;
            case "Delivered":
                holder.v_status.getBackground().setTint(Color.parseColor("#37A425"));
                break;
            case "Delivering":
                holder.v_status.getBackground().setTint(Color.parseColor("#E5D32A"));
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), EditDeliveryOrder.class);
                intent.putExtra("delivery", delivery);
                intent.putExtra("deliveryitm", delivery.getItems());
                // put all that you need in intent
                view.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView v_did;
        private TextView v_tamount;
        private TextView v_status;
        private TextView v_datatime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            v_did = itemView.findViewById(R.id.delivery_id);
            v_tamount = itemView.findViewById(R.id.delivery_amount);
            v_status = itemView.findViewById(R.id.delivery_status);
            v_datatime = itemView.findViewById(R.id.delivery_datatime);

        }

    }

}

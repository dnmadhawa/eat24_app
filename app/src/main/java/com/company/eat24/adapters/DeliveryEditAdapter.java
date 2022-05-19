package com.company.eat24.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.eat24.activities.EditDeliveryOrder;
import com.company.eat24.R;
import com.company.eat24.models.DeliveryItem;

import java.util.ArrayList;

public class DeliveryEditAdapter extends RecyclerView.Adapter<DeliveryEditAdapter.ViewHolder> {

    Context context;
    ArrayList<DeliveryItem> list;

    public DeliveryEditAdapter(Context context, ArrayList<DeliveryItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DeliveryEditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_delivery_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeliveryItem deliveryItem = list.get(position);
        holder.ie_name.setText(deliveryItem.getIname());
        holder.ie_qty.setText(deliveryItem.getIqty());
        holder.ie_price.setText(deliveryItem.getIprice());
        holder.ie_status.setText(deliveryItem.getIstatus());
        switch (deliveryItem.getIstatus()){
            case "Prepared":
                holder.ie_status.getBackground().setTint(Color.parseColor("#6EE95A"));
                EditDeliveryOrder.isReady = true;
                break;
            case "Pending":
                holder.ie_status.getBackground().setTint(Color.parseColor("#75C3E4"));
                break;
            case "Preparing":
                holder.ie_status.getBackground().setTint(Color.parseColor("#E4AA75"));
                EditDeliveryOrder.isReady = true;
                break;

        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView ie_name;
        private TextView ie_qty;
        private TextView ie_price;
        private TextView ie_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ie_name = itemView.findViewById(R.id.ie_name);
            ie_qty = itemView.findViewById(R.id.ie_qty);
            ie_price = itemView.findViewById(R.id.ie_price);
            ie_status = itemView.findViewById(R.id.ie_status);

        }

    }

}

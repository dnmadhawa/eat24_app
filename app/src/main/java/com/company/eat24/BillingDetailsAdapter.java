package com.company.eat24;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class BillingDetailsAdapter extends RecyclerView.Adapter<BillingDetailsAdapter.viewHolder>{

    ArrayList<BillingModel> list;


    public BillingDetailsAdapter(ArrayList<BillingModel> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.billingd_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        BillingModel billingModel = list.get(position);
         holder.item.setText("#"+billingModel.getOrderid()+" - "+ billingModel.getTotal());
         holder.date.setText(billingModel.getDatetime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), SingleBill.class);
                intent.putExtra("Model", billingModel);
                intent.putExtra("billingitem", billingModel.getItem());
                // put all that you need in intent
                view.getContext().startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{

     TextView item,date;


        public viewHolder(@NonNull View itemView){
            super(itemView);
            item = itemView.findViewById(R.id.bd_id_price);
            date = itemView.findViewById(R.id.bd_date);


        }
    }
}

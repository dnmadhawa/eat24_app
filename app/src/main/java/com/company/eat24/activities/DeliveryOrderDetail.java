package com.company.eat24.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.eat24.R;
import com.company.eat24.adapters.DeliveryEditAdapter;
import com.company.eat24.models.Delivery;
import com.company.eat24.models.DeliveryItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeliveryOrderDetail extends AppCompatActivity {
    Delivery delivery;

    RecyclerView recyclerView;

    DatabaseReference databaseReference;

    ArrayList<DeliveryItem> list;

    public static boolean isReady ;

    TextView e_id, e_status, e_date, et_delivery_fee, e_total,e_name, e_number, e_address, e_note, e_delivery_fee;


    Button deliveryBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_order_detail);
        delivery = (Delivery) getIntent().getSerializableExtra("delivery");
        isReady = false;
        list = new ArrayList<>();

        ArrayList<HashMap> ilist = (ArrayList<HashMap>) getIntent().getSerializableExtra("deliveryitm");
        ilist.forEach((e) -> {
//            System.out.println(e);
            Map<String, Object> map = (HashMap<String, Object>) e;
//            System.out.println(map.get("foodname").toString());
            DeliveryItem deliveryItem = new DeliveryItem(map.get("foodname").toString(), map.get("qty").toString(), map.get("total").toString(), map.get("status").toString());
            list.add(deliveryItem);

        });

        recyclerView = (RecyclerView) findViewById(R.id.edit_items_RecyclerView);
        DeliveryEditAdapter deliveryEditAdapter = new DeliveryEditAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(deliveryEditAdapter);


        e_id = (TextView) findViewById(R.id.e_id);
        e_status = (TextView) findViewById(R.id.e_status);
        e_date = (TextView) findViewById(R.id.e_date);
        et_delivery_fee = (TextView) findViewById(R.id.et_delivery_fee);
        e_total = (TextView) findViewById(R.id.e_total);
        e_name = (TextView) findViewById(R.id.e_name);
        e_number = (TextView) findViewById(R.id.e_number);
        e_address = (TextView) findViewById(R.id.e_address);
        e_note = (TextView) findViewById(R.id.e_note);
        e_delivery_fee = (TextView) findViewById(R.id.e_delivery_fee);

        e_id.setText("#00" + delivery.getD_id());
        e_status.setText(delivery.getDelivery_status());
        e_date.setText(delivery.getDatetime());
        et_delivery_fee.setText(delivery.getDelivery_fee());
        e_total.setText(delivery.getTotal_amount());
        e_name.setText(delivery.getCusName());
//        System.out.println(delivery.getMobile_number());
        e_number.setText(delivery.getMobile_number());
        e_address.setText(delivery.getAddress());
        e_note.setText(delivery.getNote());
        e_delivery_fee.setText(delivery.getDelivery_fee());
        deliveryBtn = (Button) findViewById(R.id.deliveryBtn);
        switch (delivery.getDelivery_status()){
            case "Not Delivered":
                e_status.getBackground().setTint(Color.parseColor("#E47575"));
                break;
            case "Delivered":
                e_status.getBackground().setTint(Color.parseColor("#37A425"));
                break;
            case "Delivering":
                e_status.getBackground().setTint(Color.parseColor("#E5D32A"));
                break;
        }

        switch (delivery.getDelivery_status()) {
            case "Not Delivered":
                deliveryBtn.setText("Delivering");
                break;
            case "Delivering":
                deliveryBtn.setText("Delivered");
                break;
        }

        deliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus();
            }
        });


    }

    private void changeStatus(){
        databaseReference = FirebaseDatabase.getInstance().getReference("delivery");
        databaseReference.child(delivery.getD_id()).child("delivery_status").setValue(deliveryBtn.getText().toString()).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DeliveryOrderDetail.this, "Order Status Updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DeliveryOrderDetail.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onBack(View view) {
        super.onBackPressed();

    }
}
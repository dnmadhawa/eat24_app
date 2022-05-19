package com.company.eat24.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditDeliveryOrder extends AppCompatActivity {

    Delivery delivery;

    RecyclerView recyclerView;

    DatabaseReference databaseReference;

    ArrayList<DeliveryItem> list;

    public static boolean isReady ;

    TextView e_id, e_status, e_date, et_delivery_fee, e_total;
    EditText e_name, e_number, e_address, e_note, e_delivery_fee;

    Button updatebtn, deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delivery_order);
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
        e_name = (EditText) findViewById(R.id.e_name);
        e_number = (EditText) findViewById(R.id.e_number);
        e_address = (EditText) findViewById(R.id.e_address);
        e_note = (EditText) findViewById(R.id.e_note);
        e_delivery_fee = (EditText) findViewById(R.id.e_delivery_fee);

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

        switch (delivery.getDelivery_status()) {
            case "Not Delivered":
                e_status.getBackground().setTint(Color.parseColor("#E47575"));
                break;
            case "Delivered":
                e_status.getBackground().setTint(Color.parseColor("#37A425"));
                break;
        }

        updatebtn = (Button) findViewById(R.id.do_updateBtn);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        deletebtn = (Button) findViewById(R.id.do_deleteBtn);

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                databaseReference = FirebaseDatabase.getInstance().getReference("delivery");
                                databaseReference.child(delivery.getD_id()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(EditDeliveryOrder.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else {
                                            Toast.makeText(EditDeliveryOrder.this, "Failed to Delete", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //Do your No progress
                                break;
                        }
                    }
                };
                if (isReady) {
                    new AlertDialog.Builder(EditDeliveryOrder.this)
                            .setTitle("Delete Order")
                            .setMessage("If any meal preparing or prepared not be able to delete")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                } else {
                    AlertDialog.Builder ab = new AlertDialog.Builder(EditDeliveryOrder.this);
                    ab.setMessage("Are you sure to delete?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }

            }
        });

    }

    private void updateData() {
        HashMap u_details = new HashMap();
        u_details.put("cusName", e_name.getText().toString());
        u_details.put("mobile_number", e_number.getText().toString());
        u_details.put("address", e_address.getText().toString());
        u_details.put("note", e_note.getText().toString());
        u_details.put("delivery_fee", String.format("%1.2f", Float.parseFloat(e_delivery_fee.getText().toString())));
        Double newTot = Double.parseDouble(e_total.getText().toString()) - Double.parseDouble(et_delivery_fee.getText().toString()) + Double.parseDouble(e_delivery_fee.getText().toString());
        u_details.put("total_amount", String.format("%1.2f", newTot));

        databaseReference = FirebaseDatabase.getInstance().getReference("delivery");
        databaseReference.child(delivery.getD_id()).updateChildren(u_details).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditDeliveryOrder.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditDeliveryOrder.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onBack(View view) {
        super.onBackPressed();

    }
}
package com.company.eat24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import android.widget.EditText;
//import android.widget.ListAdapter;
//import android.widget.Toast;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.ValueEventListener;
//import com.example.crud_firebase.DBHelper.DBHelper;
//import java.util.ArrayList;

public class NewDelivery extends AppCompatActivity {
    EditText name,address,delivery_fee, mobile_number,note,order_num;
    DatabaseReference reff;
    long maxid=0 ;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delivery);

        name=(EditText)findViewById(R.id.c_name);
        mobile_number=(EditText)findViewById(R.id.cm_number);
        address=(EditText)findViewById(R.id.c_address);
        note=(EditText)findViewById(R.id.c_note);
        delivery_fee=(EditText)findViewById(R.id.c_fee);

        reff = FirebaseDatabase.getInstance().getReference().child("delivery");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void submitDOrder(View view){
        HashMap hashMap = new HashMap();
        hashMap.put("cusName", name.getText().toString());
        hashMap.put("address", address.getText().toString());
        hashMap.put("delivery_fee", delivery_fee.getText().toString());
        hashMap.put("mobile_number", mobile_number.getText().toString());
        hashMap.put("note", note.getText().toString());
        hashMap.put("mobile_number", name.getText().toString());
        hashMap.put("delivery_status", "Not Delivered");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        hashMap.put("datetime", dateFormat.format(date));

        Double tot = DeliveryFoodMenu.total +Double.parseDouble(delivery_fee.getText().toString());
        hashMap.put("total_amount", String.format("%1.2f", tot));
        hashMap.put("d_id", String.valueOf(maxid+1));

        hashMap.put("items", DeliveryFoodMenu.items);
        reff.child(String.valueOf(maxid+1)).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(NewDelivery.this,"Successfully ordered", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void onBack(View view) {
        super.onBackPressed();

    }
}
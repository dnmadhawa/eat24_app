package com.company.eat24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingleBill extends AppCompatActivity {


    BillingModel billingModel;

    RecyclerView recyclerView;

    DatabaseReference databaseReference;

    ArrayList<OrderModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_bill);
        billingModel = (BillingModel) getIntent().getSerializableExtra("Model");

        list = new ArrayList<>();

        ArrayList<HashMap> ilist = (ArrayList<HashMap>) getIntent().getSerializableExtra("billingitem");

        ilist.forEach((e) -> {
//            System.out.println(e);
//
            Map<String, Object> map = (HashMap<String, Object>) e;
//            System.out.println(map.get("total").toString());
            OrderModel orderModel = new OrderModel();
            orderModel.setItem(map.get("item").toString());
            orderModel.setQuantity(map.get("quantity").toString());
            orderModel.setPrice(map.get("price").toString());
            list.add(orderModel);
//
        });

        recyclerView = (RecyclerView) findViewById(R.id.sb_RecyclerView);
        SingleBillingAdapter singleBillingAdapter = new SingleBillingAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(singleBillingAdapter);

        TextView sb_id = (TextView) findViewById(R.id.sb_id);
        TextView sb_date = (TextView) findViewById(R.id.sb_date);
        TextView sb_subtotal = (TextView) findViewById(R.id.sb_subtotal);
        TextView sb_discount = (TextView) findViewById(R.id.sb_discount);
        TextView sb_total = (TextView) findViewById(R.id.sb_total);

        sb_id.setText("#" + billingModel.getOrderid());
        sb_date.setText(billingModel.getDatetime());
        sb_subtotal.setText(billingModel.getSubtotal());
        sb_discount.setText(billingModel.getDiscount());
        sb_total.setText(billingModel.getTotal());
    }

    public void onBack(View view) {
        super.onBackPressed();

    }

    public void onDelete(View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        databaseReference = FirebaseDatabase.getInstance().getReference("bill");
                        databaseReference.child(billingModel.getOrderid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(SingleBill.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(SingleBill.this, "Failed to Delete", Toast.LENGTH_SHORT).show();
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
            AlertDialog.Builder ab = new AlertDialog.Builder(SingleBill.this);
            ab.setMessage("Are you sure to delete?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();


    }
}
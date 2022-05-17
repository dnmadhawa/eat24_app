package com.company.eat24.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.company.eat24.R;
import com.company.eat24.adapters.DeliveryFoodAdapter;
import com.company.eat24.models.Food;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DeliveryFoodMenu extends AppCompatActivity {
    int minteger = 1;
    View bottomlayout;
    TextView qtytext;
    LinearLayout ly;
    public static String fname, fprice;
    public static Double total;
    BottomSheetDialog sheet;


    public static ArrayList<HashMap> items;


    RecyclerView recyclerView;
    DatabaseReference database;
    DeliveryFoodAdapter deliveryFoodAdapter;
    ArrayList<Food> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_food_menu);

        total = 0.00;

        CardView asian_foods = findViewById(R.id.d_asian_foods);
        CardView pasta = findViewById(R.id.d_pasta);
        CardView kottu = findViewById(R.id.d_kottu);
        CardView pizza = findViewById(R.id.d_pizza);
        CardView buns = findViewById(R.id.d_buns);
        CardView beverages = findViewById(R.id.d_beverages);
        asian_foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Asian Foods");
            }
        });
        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Pasta");
            }
        });
        kottu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Kottu");
            }
        });
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Pizza");
            }
        });
        buns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Buns");
            }
        });
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Beverages");
            }
        });

        items = new ArrayList();
    }

    private void showBottomSheetDialog(String cat) {
        minteger = 1;
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);


        View bottomlayout = LayoutInflater.from(DeliveryFoodMenu.this).inflate(R.layout.bottom_delivery_food_layout, ly);
        bottomSheetDialog.setContentView(bottomlayout);


        recyclerView = bottomlayout.findViewById(R.id.dfood_list);
        database = FirebaseDatabase.getInstance().getReference("food");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        list = new ArrayList<>();
        deliveryFoodAdapter = new DeliveryFoodAdapter(this, list);
        recyclerView.setAdapter(deliveryFoodAdapter);

        database.orderByChild("category").equalTo(cat).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Food food = dataSnapshot.getValue(Food.class);
                    list.add(food);
                }
//                System.out.println(list);
                deliveryFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        bottomSheetDialog.show();
        sheet = bottomSheetDialog;
        qtytext = (TextView) bottomlayout.findViewById(R.id.delivery_food_qty);
        display(1);

    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        this.display(minteger);

    }

    public void decreaseInteger(View view) {
        minteger = minteger - 1;
        this.display(minteger);
    }

    private void display(int number) {

        qtytext.setText("" + number);
    }

    public void onBack(View view) {
        super.onBackPressed();

    }

    public void addDOrder(View view) {
        if (fname != null && fprice != null) {
            Toast.makeText(this, "Item added", Toast.LENGTH_LONG).show();
            sheet.dismiss();
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("foodname", fname);
            item.put("foodprice", fprice);
            item.put("qty", Integer.toString(minteger));
            item.put("status", "Panding");
            Double t = Double.parseDouble(fprice) * minteger;
//            System.out.println(total);
            total =total+ t;
            item.put("total", String.format("%1.2f", t));
            items.add(item);


        }

    }

    public void orderdetails(View view) {
        if (items.isEmpty()) {
            Toast.makeText(this, "Please select the food", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, NewDelivery.class);
            startActivity(intent);
        }

    }
}
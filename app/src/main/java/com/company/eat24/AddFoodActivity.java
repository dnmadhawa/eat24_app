package com.company.eat24;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity {

    EditText name,size,prize,url,category;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        name = (EditText)findViewById(R.id.textNameAdd);
        size = (EditText)findViewById(R.id.textSizeAdd);
        prize = (EditText)findViewById(R.id.textPrizeAdd);
        url = (EditText)findViewById(R.id.textImgUrlAdd);
        category = (EditText)findViewById(R.id.textCategoryAdd);

        btnAdd = (Button)findViewById(R.id.btnFoodAdd);
        btnBack = (Button)findViewById(R.id.btnFoodBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void insertData() {

        Map<String , Object > map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("size", size.getText().toString());
        map.put("prize", prize.getText().toString());
        map.put("furl", url.getText().toString());
        map.put("category", category.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("food").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddFoodActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddFoodActivity.this, "Error occurred while inserting", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    private void clearAll(){

        name.setText("");
        size.setText("");
        prize.setText("");
        url.setText("");
        category.setText("");

    }
}
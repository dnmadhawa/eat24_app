package com.company.eat24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.lights.LightState;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private Object CategoryModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        private void loadCategoryFromFireBase(){
            List<CategoryModel> categoryModelList =new ArrayList<>();
            FirebaseDatabase.getInstance()
                    .getReference("Category")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Object categoryLoadListner = null;
                            if(dataSnapshot.exists()){
                                for(dataSnapshot:dataSnapshot:
                                dataSnapshot.getChildren()) {
                                    HttpCookie categorySnapshot = null;
                                    String categoryModel = categorySnapshot.getValue(CategoryModel);
                                    categoryModel.add(categoryModel);

                                }categoryLoadListner.onCategoryLoadSuccess(categoryModelList);

                            }
                            else{
                                categoryLoadListner.onCategoryLoadFail("can not find items");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }
    }
}
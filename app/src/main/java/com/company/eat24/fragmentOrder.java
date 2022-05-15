package com.company.eat24;

import android.R.layout;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragmentOrder extends Fragment {
    Spinner spinner;
    CardView btnadd;
    DatabaseReference dbref;
    RecyclerView recyclerView;

    ValueEventListener listener;
    ArrayList<OrderModel> list;
    OrderAdapter adapter;

    String[] table = {"table01","table02","table03","table04","table05","table06"};




    public fragmentOrder() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);


        recyclerView = view.findViewById(R.id.recycler);
        btnadd = view.findViewById(R.id.addbtn);
        dbref = FirebaseDatabase.getInstance().getReference("Orders");



        Spinner spino = view.findViewById(R.id.spinner);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(getContext(), layout.simple_spinner_item, table);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spino.setAdapter(ad);
        spino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println(table[i]);
                fetchdata(table[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






       //fetchdata();
        //noinspection rawtypes

        //spinner.setAdapter(adapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return view;


    }
    public void fetchdata(String td){
        list = new ArrayList<OrderModel>();
       dbref.orderByChild("tableNo").equalTo(td).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              for(DataSnapshot mydata:snapshot.getChildren()) {
                  OrderModel model = mydata.getValue(OrderModel.class);
                  list.add(model);
              }

                  adapter.notifyDataSetChanged();

          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

        adapter = new OrderAdapter(list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerView.setAdapter(adapter);

    }










}
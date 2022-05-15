package com.company.eat24;

import android.R.layout;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragmentOrder extends Fragment {
    Spinner spinner;
    Button btnadd;
    DatabaseReference dbref;
    RecyclerView recyclerView;

    ValueEventListener listener;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;




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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        spinner = view.findViewById(R.id.spinner);
        btnadd = view.findViewById(R.id.addbtn);
        dbref = FirebaseDatabase.getInstance().getReference("Orders");

        list = new ArrayList<String>();
        //noinspection rawtypes
        adapter = new ArrayAdapter<>(fragmentOrder.this,
                layout.simple_spinner_dropdown_item,
                list);
        spinner.setAdapter(adapter);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fetchdata();

        return view;


    }
    public void fetchdata(){
      listener = dbref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              for(DataSnapshot mydata:snapshot.getChildren())
                  list.add(mydata.getValue().toString());
                  adapter.notifyDataSetChanged();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });
    }






}
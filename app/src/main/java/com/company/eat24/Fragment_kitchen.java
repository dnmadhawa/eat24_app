package com.company.eat24;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment_kitchen extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<KitchenModel> list;
    KitchenAdapter adapter;
    DatabaseReference database;


    public Fragment_kitchen() {

    }

    public static Fragment_kitchen newInstance(String param1, String param2) {
        Fragment_kitchen fragment = new Fragment_kitchen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_kitchen, container, false);

       recyclerView = view.findViewById(R.id.recycleview);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
       //FirebaseRecyclerOptions<KitchenModel>options = new FirebaseRecyclerOptions.Builder<KitchenModel>().setQuery
             //  (FirebaseDatabase.getInstance().getReference().child("Orders"),KitchenModel.class).build();

        database = getInstance().getReference("Orders");
        recyclerView.setHasFixedSize(true);



         list = new ArrayList<>();
         adapter = new KitchenAdapter(this,list);
         recyclerView.setAdapter(adapter);

         database.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    KitchenModel  kitchenModel = dataSnapshot.getValue(KitchenModel.class);
                    list.add(kitchenModel);
                 }
                 adapter.notifyDataSetChanged();

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


       return view;
    }






}
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
import java.util.HashMap;
import java.util.Map;


public class Fragment_kitchen extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    int i = 0;
    RecyclerView recyclerView;
    ArrayList<OrderModel> list;
    ArrayList<OrderModel> list1;
    KitchenAdapter adapter;
    KitchenDAdapter adapter2;
    DatabaseReference database;
    DatabaseReference ref;


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


       //FirebaseRecyclerOptions<KitchenModel>options = new FirebaseRecyclerOptions.Builder<KitchenModel>().setQuery
             //  (FirebaseDatabase.getInstance().getReference().child("Orders"),KitchenModel.class).build();

        database = getInstance().getReference("Orders");







         database.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 list = new ArrayList<>();
                 for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    OrderModel  kitchenModel = dataSnapshot.getValue(OrderModel.class);
                    list.add(kitchenModel);
                 }
                 recyclerView = view.findViewById(R.id.recycleview);
                 recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
                 recyclerView.setHasFixedSize(true);
                 adapter = new KitchenAdapter(list);
                 recyclerView.setAdapter(adapter);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

        ref = getInstance().getReference("delivery");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                    ArrayList<HashMap> items =  (ArrayList<HashMap>) td.get("items");
                    i=0;
                    items.forEach((e) -> {
                        Map<String, Object> data = (HashMap<String, Object>)  e;
                        System.out.println(data.get("foodname"));
                        OrderModel  kitchenModel = new OrderModel(Integer.toString(i) , data.get("foodname").toString(), data.get("qty").toString(), data.get("status").toString(), td.get("d_id").toString());
                        list1.add(kitchenModel);
                        i= i+1;
                    });
                    System.out.println(list1);
                }
                recyclerView = view.findViewById(R.id.Drecycleview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
                recyclerView.setHasFixedSize(true);
                adapter2 = new KitchenDAdapter(list1);
                recyclerView.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       return view;
    }






}
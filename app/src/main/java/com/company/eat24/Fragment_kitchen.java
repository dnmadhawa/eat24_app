package com.company.eat24;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class Fragment_kitchen extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<KitchenModel> dataholder;
    KitchenAdapter kitchenAdapter;



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
       FirebaseRecyclerOptions<KitchenModel>options = new FirebaseRecyclerOptions.Builder<KitchenModel>().setQuery
               (FirebaseDatabase.getInstance().getReference().child("Orders"),KitchenModel.class).build();

       kitchenAdapter  = new KitchenAdapter(options);
       recyclerView.setAdapter(kitchenAdapter);


       //dataholder = new ArrayList<>();
         /*KitchenModel ob1 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob1);
        KitchenModel ob2 = new KitchenModel(R.drawable.dish,"#332","2","1","Mix Rice s","Preparing");
       dataholder.add(ob2);*/
        //recyclerView.setAdapter(new KitchenAdapter(dataholder));


       return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        kitchenAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        kitchenAdapter.stopListening();
    }



}
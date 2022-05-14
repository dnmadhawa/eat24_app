package com.company.eat24;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment_kitchen extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<KitchenModel> dataholder;

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
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       dataholder = new ArrayList<>();

       KitchenModel ob1 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob1);
        KitchenModel ob2 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob2);
        KitchenModel ob3 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob3);
        KitchenModel ob4 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob4);
        KitchenModel ob5 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob5);
        KitchenModel ob6 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob6);
        KitchenModel ob7 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob7);
        KitchenModel ob8 = new KitchenModel(R.drawable.dish,"#333","1","1","Mix Rice L","Preparing");
       dataholder.add(ob8);

       recyclerView.setAdapter(new KitchenAdapter(dataholder));



       return view;
    }
}
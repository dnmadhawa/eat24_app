package com.company.eat24.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.company.eat24.activities.DeliveryFoodMenu;
import com.company.eat24.R;
import com.company.eat24.adapters.DeliveryAdapter;
import com.company.eat24.models.Delivery;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeliveryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveryFragment extends Fragment {


    RecyclerView recyclerView;

    DatabaseReference database;

    ArrayList<Delivery> list;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public static DeliveryFragment newInstance(String param1, String param2) {
        DeliveryFragment fragment = new DeliveryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery, container, false);

        recyclerView= (RecyclerView) view.findViewById(R.id.dRecyclerView);



        database = FirebaseDatabase.getInstance().getReference("delivery");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                    String d_id=td.get("d_id").toString();
                    String datetime=td.get("datetime").toString();
                    String address=td.get("address").toString();
                    String cusName=td.get("cusName").toString();
                    String delivery_fee=td.get("delivery_fee").toString();
                    String delivery_status=td.get("delivery_status").toString();
                    String mobile_number=td.get("mobile_number").toString();
                    String note=td.get("note").toString();
                    String total_amount=td.get("total_amount").toString();
                    ArrayList<HashMap> items;
//                    System.out.println(td.get("mobile_number").toString());
                    items= (ArrayList<HashMap>) td.get("items");
                    Delivery delivery = new Delivery(d_id,datetime,address,cusName,delivery_fee,delivery_status,mobile_number,note,total_amount,items);
                    list.add(delivery);

                }
                DeliveryAdapter deliveryAdapter = new DeliveryAdapter(getContext(),list);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, true);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(deliveryAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        Button btn1 = (Button) view
                .findViewById(R.id.new_order_menu);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), DeliveryFoodMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        return view;
    }


}
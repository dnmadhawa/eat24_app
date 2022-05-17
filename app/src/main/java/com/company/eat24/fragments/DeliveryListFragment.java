package com.company.eat24.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.eat24.R;
import com.company.eat24.adapters.DeliveryListAdapter;
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
 * Use the {@link DeliveryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveryListFragment extends Fragment {


    RecyclerView recyclerView;

    DatabaseReference database;

    ArrayList<Delivery> list;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeliveryListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeliveryListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeliveryListFragment newInstance(String param1, String param2) {
        DeliveryListFragment fragment = new DeliveryListFragment();
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
        View view = inflater.inflate(R.layout.fragment_delivery_list, container, false);

        recyclerView= (RecyclerView) view.findViewById(R.id.d_list_recyclerView);



        database = FirebaseDatabase.getInstance().getReference("delivery");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                    if (!td.get("delivery_status").equals("Delivered")) {
                        String d_id = td.get("d_id").toString();
                        String datetime = td.get("datetime").toString();
                        String address = td.get("address").toString();
                        String cusName = td.get("cusName").toString();
                        String delivery_fee = td.get("delivery_fee").toString();
                        String delivery_status = td.get("delivery_status").toString();
                        String mobile_number = td.get("mobile_number").toString();
                        String note = td.get("note").toString();
                        String total_amount = td.get("total_amount").toString();
                        ArrayList<HashMap> items;
//                    System.out.println(td.get("mobile_number").toString());
                        items = (ArrayList<HashMap>) td.get("items");
                        Delivery delivery = new Delivery(d_id, datetime, address, cusName, delivery_fee, delivery_status, mobile_number, note, total_amount, items);
                        list.add(delivery);
                    }

                }
                DeliveryListAdapter deliveryAdapter = new DeliveryListAdapter(getContext(),list);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, true);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(deliveryAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}
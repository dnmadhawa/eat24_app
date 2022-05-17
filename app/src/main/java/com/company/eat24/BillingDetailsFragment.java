package com.company.eat24;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Use the {@link BillingDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillingDetailsFragment extends Fragment {

    RecyclerView recyclerView;

    DatabaseReference database;

    ArrayList<BillingModel> list;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BillingDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillingDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillingDetailsFragment newInstance(String param1, String param2) {
        BillingDetailsFragment fragment = new BillingDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_billing_details, container, false);

        recyclerView= (RecyclerView) view.findViewById(R.id.bd_recyclerView);



        database = FirebaseDatabase.getInstance().getReference("bill");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();

                        String datetime = td.get("datetime").toString();
                        String discount = td.get("discount").toString();
                        String orderid = td.get("orderid").toString();
                        String subtotal = td.get("subtotal").toString();
                        String total = td.get("total").toString();
                        ArrayList<HashMap> items;
//                    System.out.println(td.get("mobile_number").toString());
                        items = (ArrayList<HashMap>) td.get("items");
                        BillingModel billingModel = new BillingModel(datetime, discount, orderid, subtotal, total, items);
                        list.add(billingModel);


                }
                BillingDetailsAdapter deliveryAdapter = new BillingDetailsAdapter(list);
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
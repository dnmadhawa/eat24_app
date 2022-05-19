package com.company.eat24;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillingFragment extends Fragment {
    String[] table={"table01","table02","table03","table04","table05","table06"};
    ArrayList<OrderModel> list;
    DatabaseReference dbref;
    RecyclerView recyclerView;
    BillingAdapter adapter;
    ArrayList<HashMap> items;
    DatabaseReference reff;
    TextView stot;
    TextView tot ;
    EditText dis ;
    double sub = 0.00;
    long maxid=0 ;

    String tableNo;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BillingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillingFragment newInstance(String param1, String param2) {
        BillingFragment fragment = new BillingFragment();
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
        View view = inflater.inflate(R.layout.fragment_billing, container, false);
         stot = view.findViewById(R.id.subtot);
         tot = view.findViewById(R.id.total);
         dis = view.findViewById(R.id.discount);

        recyclerView = view.findViewById(R.id.billingRecycler);
        dbref = FirebaseDatabase.getInstance().getReference("Orders");

        Spinner spino = view.findViewById(R.id.spn);
        spino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tableNo =table[i];

                dbref.orderByChild("tableNo").equalTo(table[i]).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list = new ArrayList<OrderModel>();
                        items = new ArrayList();
                        sub = 0.00;
                        for(DataSnapshot mydata:snapshot.getChildren()) {
                            OrderModel model = mydata.getValue(OrderModel.class);
                            HashMap item = new HashMap<String, String>();
                            item = (HashMap) mydata.getValue();
                            items.add(item);
                            list.add(model);
                            sub= sub + Double.parseDouble(model.getPrice());


                        }
                        stot.setText(String.format("%1.2f", sub));
                        dis.setText("0.00");
                        tot.setText(String.format("%1.2f", sub));
                        adapter = new BillingAdapter(list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, table);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spino.setAdapter(ad);


        dis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (dis.getText().toString().trim().length() > 0){
                    Double total =sub -  Double.parseDouble(dis.getText().toString());
                    tot.setText(String.format("%1.2f", total));
                }else {
                    tot.setText(String.format("%1.2f", sub));
                }

            }
        });


        Button btn = view.findViewById(R.id.paybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitDOrder();
            }
        });

        reff = FirebaseDatabase.getInstance().getReference().child("bill");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists())
//                    maxid = (snapshot.getChildrenCount());
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                    maxid =Integer.parseInt(td.get("orderid").toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    public void submitDOrder(){
        HashMap hashMap = new HashMap();
        hashMap.put("subtotal",String.format("%1.2f", Double.parseDouble(stot.getText().toString())));
        hashMap.put("discount", String.format("%1.2f", Double.parseDouble(dis.getText().toString())));
        hashMap.put("total",  String.format("%1.2f", Double.parseDouble(tot.getText().toString())));
        hashMap.put("items", items);
        hashMap.put("orderid", String.valueOf(maxid+1));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        hashMap.put("datetime", dateFormat.format(date));



        reff.child(String.valueOf(maxid+1)).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(),"Successfully ordered", Toast.LENGTH_LONG).show();

            }
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Orders").orderByChild("tableNo").equalTo(tableNo);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
//                replaceFragment(new BillingFragment());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



}
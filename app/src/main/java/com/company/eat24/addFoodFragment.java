package com.company.eat24;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addFoodFragment extends Fragment {


    EditText name,size,prize,url,category;
    Button btnAdd, btnBack;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addFoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addFoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addFoodFragment newInstance(String param1, String param2) {
        addFoodFragment fragment = new addFoodFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_food, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (EditText)view.findViewById(R.id.textNameAdd);
        size = (EditText)view.findViewById(R.id.textSizeAdd);
        prize = (EditText)view.findViewById(R.id.textPrizeAdd);
        url = (EditText)view.findViewById(R.id.textImgUrlAdd);
        category = (EditText)view.findViewById(R.id.textCategoryAdd);

        btnAdd = (Button)view.findViewById(R.id.btnFoodAdd);
        btnBack = (Button)view.findViewById(R.id.btnFoodBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), foodActivity.class);
                startActivity(intent);
            }
        });
    }



    private void insertData() {

        Map<String , Object > map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("size", size.getText().toString());
        map.put("prize", prize.getText().toString());
        map.put("furl", url.getText().toString());
        map.put("category", category.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("food").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(getActivity(), "Error occurred while inserting", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    private void clearAll(){

        name.setText("");
        size.setText("");
        prize.setText("");
        url.setText("");
        category.setText("");

    }

}
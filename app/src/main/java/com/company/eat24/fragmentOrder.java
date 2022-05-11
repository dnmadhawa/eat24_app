package com.company.eat24;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class fragmentOrder extends Fragment {
    Spinner dropdown;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public fragmentOrder() {

    }



    public static fragmentOrder newInstance(String param1, String param2) {
        fragmentOrder fragment = new fragmentOrder();
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
        View rootView = inflater.inflate(R.layout.fragment_order,container,false);

        dropdown = rootView.findViewById(R.id.spinner);
        initspinnerfooter();

        return rootView;



    }

    private void initspinnerfooter() {

        String[] items = new String[]{
                "1","2","3","4","5","6","7","8","9","10",
        };

        ArrayAdapter<String>adapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
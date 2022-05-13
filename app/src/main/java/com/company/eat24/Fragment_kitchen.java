package com.company.eat24;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment_kitchen<ActivityKitchenBinding> extends Fragment {

 ActivityKitchenBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

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
        binding = ActivityKitchenBinding.inflate(getLayoutInflater());
        ArrayList<KitchenModel>list = new ArrayList<>();
        list.add(new KitchenModel(R.drawable.dish,"123","1","1","Kottu L","Preparing"));
        list.add(new KitchenModel(R.drawable.dish,"124","2","2","Mix Rice N","Prepared"));
        list.add(new KitchenModel(R.drawable.dish,"125","3","1","Orange juice","Preparing"));
        list.add(new KitchenModel(R.drawable.dish,"126","4","2","Chees Pasta","Prepared"));
        list.add(new KitchenModel(R.drawable.dish,"127","5","1","Kottu","Preparing"));
        list.add(new KitchenModel(R.drawable.dish,"128","6","2","Kottu","Preparing"));
        list.add(new KitchenModel(R.drawable.dish,"129","7","1","Kottu","Preparing"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_kitchen, container, false);
    }
}
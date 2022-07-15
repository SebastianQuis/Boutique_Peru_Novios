package com.example.dalisaapp.venta.dashclient.Tienda.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dalisaapp.R;


public class FgPromocion extends Fragment {


    private  String dataOne;
    private  String dataTwo;

    public FgPromocion(){

    }

    public FgPromocion(String dataOne, String dataTwo) {
        this.dataOne = dataOne;
        this.dataTwo = dataTwo;
    }

    public static FgPromocion newInstance(String param1, String param2) {
        FgPromocion fragment = new FgPromocion();
        Bundle args = new Bundle();
        args.putString("dataOne",param1);
        args.putString("dataTwo",param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataOne = getArguments().getString("dataOne");
            dataTwo = getArguments().getString("dataTwo");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_fg_promocion, container, false);
         //TextView dataOne= v.findViewById(R.id.Data1);
         //TextView dataTwo= v.findViewById(R.id.Data2);
         //dataOne.setText(this.dataOne);
         //dataTwo.setText(this.dataTwo);
        return v;
    }
}
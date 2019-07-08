package com.example.requestapi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    // UI components
    Button taipeiBtn;
    Button taichungBtn;
    Button tainanBtn;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Initialize UI button
        taipeiBtn = view.findViewById(R.id.TaipeiButton);
        taichungBtn = view.findViewById(R.id.TaichungButton);
        tainanBtn = view.findViewById(R.id.TainanButton);

        return view;
    }

}

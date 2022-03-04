package com.example.virtualcloset.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

//import android.R;

import androidx.fragment.app.Fragment;

import com.example.virtualcloset.R;

public class DetailView extends Fragment {

    private String bName;
    private ArrayList<String> bTags; //*******
    private String bImg;

    public DetailView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bName = getArguments().getString("name");
            bTags = getArguments().getStringArrayList("tags");      //******
            bImg = getArguments().getString("img");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_detail_view, container, false);
        TextView nameDisplay = (TextView) view.findViewById(R.id.nameDisplay);
        nameDisplay.setText(bName);

        //ImageView imageView = (ImageView) view.findViewById(R.id.imageDisplay);
        //imageDisplay.setImage(bImg);


        return view;
    }
}
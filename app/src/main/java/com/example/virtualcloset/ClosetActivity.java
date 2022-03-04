package com.example.virtualcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.virtualcloset.databinding.ActivityClosetBinding;


public class ClosetActivity extends AppCompatActivity {

    ActivityClosetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClosetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        Database database = new Database();
        String[] clothesNames = database.getClothesItems();
        GridAdapter gridAdapter = new GridAdapter(ClosetActivity.this,clothesNames,R.drawable.placehold);

        binding.gridView.setAdapter(gridAdapter);
    }
}
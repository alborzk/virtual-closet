package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.databinding.ActivityOutfitBinding;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.logic.OufitDataManager;
import com.example.virtualcloset.storage.Database;

public class OutfitActivity extends AppCompatActivity {

    ActivityOutfitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Database database = new Database();
        OufitDataManager dm = new OufitDataManager(database);

        String[] clothesNames = dm.getNames();
        String[] allTags = dm.getTags();
        int[] imgs = dm.getImgs();

        GridAdapter gridAdapter = new GridAdapter(OutfitActivity.this,clothesNames, imgs);
        binding.gridview2.setAdapter(gridAdapter);


    }



}
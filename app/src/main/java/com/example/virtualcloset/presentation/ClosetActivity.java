package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.storage.Database;
import com.example.virtualcloset.databinding.ActivityClosetBinding;
import com.example.virtualcloset.logic.GridAdapter;

import java.io.Serializable;


public class ClosetActivity extends AppCompatActivity {

    ActivityClosetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClosetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Database database = new Database();
        DataManager dm = new DataManager(database);

        String[] clothesNames = dm.getNames();
        String[] allTags = dm.getTags();
        int[] imgs = dm.getImgs();

        GridAdapter gridAdapter = new GridAdapter(ClosetActivity.this,clothesNames, imgs);

        binding.gridView.setAdapter(gridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(ClosetActivity.this,"You clicked on "+ clothesNames[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ClosetActivity.this, DetailActivity.class);
                String name = clothesNames[position];
                intent.putExtra("clothingName", name);
                String tags = allTags[position];
                intent.putExtra("itemTags", tags);
                int img = imgs[position];
                intent.putExtra("itemImg", img);
                startActivity(intent);


            }
        });

        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClosetActivity.this, AddClothingActivity.class);
//                intent.putExtra("db", database);
//                DataManager dm2 = dm;
//                intent.putExtra("d2", (Parcelable) dm2);
                startActivity(intent);
            }
        });
    }
}
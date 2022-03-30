package com.example.virtualcloset.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.R;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.storage.Database;
import com.example.virtualcloset.databinding.ActivityClosetBinding;
import com.example.virtualcloset.logic.GridAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;


public class ClosetActivity extends AppCompatActivity {

    ActivityClosetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClosetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        DataManager dm = new DataManager(database);

        String[] clothesNames = dm.getNames();
        //String[] allTags = dm.getTags();
        int[] imgs = dm.getImgs();

        GridAdapter gridAdapter = new GridAdapter(ClosetActivity.this,clothesNames, imgs);

        binding.gridView.setAdapter(gridAdapter);

        // Navbar
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_clothes);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navigation_outfits:
                        Intent intent = new Intent(getApplicationContext(), OutfitListActivity.class);
                        intent.putExtra("db", database);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_clothes:
                        return true;
                }
                return false;
            }
        });


        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//              Toast.makeText(ClosetActivity.this,"You clicked on "+ clothesNames[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ClosetActivity.this, DetailActivity.class);
                intent.putExtra("db", database);
                intent.putExtra("index", position);
                intent.putExtra("dm", dm);

                startActivity(intent);

            }
        });

        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClosetActivity.this, AddClothingActivity.class);
                intent.putExtra("db", database);
                startActivity(intent);
            }
        });
    }
}
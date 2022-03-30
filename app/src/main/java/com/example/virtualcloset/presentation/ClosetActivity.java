package com.example.virtualcloset.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.logic.ClosetManager;
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

        //Receive Database
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        UserAccount account = (UserAccount) intent.getSerializableExtra("acc");
        Closet closet = (Closet) intent.getSerializableExtra("closet");
        DataManager dm = new DataManager(database);
        ClosetManager cm = new ClosetManager(closet);

        //Initialize GridAdapter
        String[] clothesNames = cm.getNames();
        int[] imgs = cm.getImgs();
        GridAdapter gridAdapter = new GridAdapter(ClosetActivity.this,clothesNames, imgs);
        binding.gridView.setAdapter(gridAdapter);

        //Navigation Bar
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
                        intent.putExtra("acc", account);
                        intent.putExtra("closet", closet);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_clothes:
                        return true;
                }
                return false;
            }
        });

        //Clicking an item in the grid
        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Go to DetailActivity with current item
                ClothesItem curr = closet.getClothesItems().get(position);
                Intent intent = new Intent(ClosetActivity.this, DetailActivity.class);
                intent.putExtra("db", database);
                intent.putExtra("acc", account);
                intent.putExtra("closet", closet);
                intent.putExtra("curr", curr);

                intent.putExtra("index", position);
                intent.putExtra("dm", dm);

                startActivity(intent);
            }
        });

        //Clicking the add item button
        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to AddClothingActivity
                Intent intent = new Intent(ClosetActivity.this, AddClothingActivity.class);
                intent.putExtra("db", database);
                intent.putExtra("acc", account);
                intent.putExtra("closet", closet);
                startActivity(intent);
            }
        });
    }
}
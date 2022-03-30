package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityOutfitListBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapter2;
import com.example.virtualcloset.logic.OutfitDataManager;
import com.example.virtualcloset.storage.Database;

import androidx.annotation.NonNull;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OutfitListActivity extends AppCompatActivity {
    ActivityOutfitListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and Current Item
        Intent intent = this.getIntent();
//        Database database = (Database) intent.getSerializableExtra("db");
        UserAccount account = (UserAccount) intent.getSerializableExtra("acc");
        Closet closet = (Closet) intent.getSerializableExtra("closet");
        ClosetManager cm = new ClosetManager(closet);
//        OutfitDataManager dm = new OutfitDataManager(database);

        //Initialize GridAdapter
        int[] outfitID=cm.getID();
        String [] outfitName=cm.getOutfitName();
        GridAdapter2 gridAdapter2 = new GridAdapter2(OutfitListActivity.this, outfitName);
        binding.gridOutfitList.setAdapter(gridAdapter2);

        //Navigation Bar
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_outfits);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navigation_clothes:
                        Intent intent = new Intent(getApplicationContext(), ClosetActivity.class);
                        intent.putExtra("acc", account);
                        intent.putExtra("closet", closet);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_outfits:
                        return true;
                }
                return false;
            }
        });

        //Clicking an item in the grid
        binding.gridOutfitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(ClosetActivity.this,"You clicked on "+ clothesNames[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OutfitListActivity.this, OutfitItemActivity.class);
                Outfit currOutfit = closet.getOutfits().get(position);
                intent.putExtra("acc", account);
                intent.putExtra("closet", closet);
                intent.putExtra("currOutfit",currOutfit);
//                intent.putExtra("db", database);
//
//                intent.putExtra("clothesList",clothesItems);

                startActivity(intent);

            }
        });


    }
}
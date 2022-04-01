package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityOutfitListBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapter;
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

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClosetManager cm = new ClosetManager(closet);

        //Initialize GridAdapter
        String[] outfitNames = cm.getOutfitsNames();
        int[] outfitImgs = cm.getOutfitsImgs();
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), outfitNames, outfitImgs);
        binding.gridOutfitList.setAdapter(gridAdapter);

        //Navigation Bar
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_outfits);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    //Go to LoginActivity
                    case R.id.navigation_accounts:
                        Intent i1 = new Intent(getApplicationContext(), AccountActivity.class);
                        i1.putExtra("db", database);
                        i1.putExtra("aID", aID);
                        i1.putExtra("cID", cID);
                        startActivity(i1);
                        overridePendingTransition(0,0);
                        return true;
                    //Go to OutfitListActivity
                    case R.id.navigation_outfits:
                        return true;
                    //Go to ClosetActivity
                    case R.id.navigation_clothes:
                        Intent i2 = new Intent(getApplicationContext(), ClosetActivity.class);
                        i2.putExtra("db", database);
                        i2.putExtra("aID", aID);
                        i2.putExtra("cID", cID);
                        startActivity(i2);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //Click on an item in the Grid
        binding.gridOutfitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i3 = new Intent(OutfitListActivity.this, OutfitItemActivity.class);
                i3.putExtra("db", database);
                i3.putExtra("aID", aID);
                i3.putExtra("cID", cID);
                i3.putExtra("curr", position);
                i3.putExtra("tab", 0);
                startActivity(i3);
            }
        });


    }
}
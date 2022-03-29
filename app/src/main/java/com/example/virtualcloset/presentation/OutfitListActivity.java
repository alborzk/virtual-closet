package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.R;
import com.example.virtualcloset.databinding.ActivityOutfitListBinding;
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

        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        OutfitDataManager dm = new OutfitDataManager(database);

        int[] outfitID=dm.getID();
        String [] outfitName=dm.getOutfitName();

        GridAdapter2 gridAdapter2 = new GridAdapter2(OutfitListActivity.this, outfitName);
        binding.gridOutfitList.setAdapter(gridAdapter2);

//        final Button button = (Button) findViewById(R.id.outlist_button);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_outfits);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navigation_clothes:
                        Intent intent = new Intent(getApplicationContext(), ClosetActivity.class);
                        intent.putExtra("db", database);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_outfits:
                        return true;
                }
                return false;
            }
        });

        binding.gridOutfitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(ClosetActivity.this,"You clicked on "+ clothesNames[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OutfitListActivity.this, OutfitItemActivity.class);
                int oid = outfitID[position];
                intent.putExtra("outfitID", oid);
                String name=outfitName[position];
                intent.putExtra("outfitName",name);
                intent.putExtra("db", database);
//
//                intent.putExtra("clothesList",clothesItems);

                startActivity(intent);

            }
        });


    }
}
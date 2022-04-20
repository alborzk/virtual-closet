package com.example.virtualcloset.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.storage.Database;
import com.example.virtualcloset.databinding.ActivityClosetBinding;
import com.example.virtualcloset.logic.GridAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class ClosetActivity extends AppCompatActivity {

    ActivityClosetBinding binding;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClosetBinding.inflate(getLayoutInflater());
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

        //Initialize GridView using GridAdapter
        String[] clothesNames = cm.getClothesNames();
        int[] imgs = cm.getClothesImgs();
        gridAdapter = new GridAdapter(getApplicationContext(), cm);
        binding.gridView.setAdapter(gridAdapter);

        //Set Up Tag Selection Dropdown
        Spinner tagFilter = findViewById(R.id.tagFilter);
        List<String> allUniqueTags = cm.getAllTags(closet.getClothesItems());
        allUniqueTags.add(0, "All Clothing");
        ArrayAdapter spinAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, allUniqueTags);
        tagFilter.setAdapter(spinAdapter);

        //Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_clothes);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    //Go to LoginActivity
                    case R.id.navigation_accounts:
                        Intent i1 = new Intent(getApplicationContext(), AccountActivity.class);
                        i1.putExtra("db", database);
                        i1.putExtra("aID", aID);
                        i1.putExtra("cID", cID);
                        startActivity(i1);
                        overridePendingTransition(0, 0);
                        return true;
                    //Go to OutfitListActivity
                    case R.id.navigation_outfits:
                        Intent i2 = new Intent(getApplicationContext(), OutfitListActivity.class);
                        i2.putExtra("db", database);
                        i2.putExtra("aID", aID);
                        i2.putExtra("cID", cID);
                        startActivity(i2);
                        overridePendingTransition(0, 0);
                        return true;
                    //Go to ClosetActivity
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
                //Go to DetailActivity
                Intent i3 = new Intent(getApplicationContext(), DetailActivity.class);
                i3.putExtra("db", database);
                i3.putExtra("aID", aID);
                i3.putExtra("cID", cID);
                i3.putExtra("curr", position);
                i3.putExtra("tab", 1);
                startActivity(i3);
            }
        });

        //Clicking the add item button
        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to AddClothingActivity
                Intent i4 = new Intent(getApplicationContext(), AddClothingActivity.class);
                i4.putExtra("db", database);
                i4.putExtra("aID", aID);
                i4.putExtra("cID", cID);
                startActivity(i4);
            }
        });

        binding.tagFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView < ? > adapterView, View view,int position,
            long l){
                String filter = adapterView.getItemAtPosition(position).toString();
                gridAdapter.setFilter(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
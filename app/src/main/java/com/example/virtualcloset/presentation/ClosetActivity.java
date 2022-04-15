package com.example.virtualcloset.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.storage.Database;
import com.example.virtualcloset.databinding.ActivityClosetBinding;
import com.example.virtualcloset.logic.GridAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ClosetActivity extends AppCompatActivity {

    ActivityClosetBinding binding;

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
        int selection = (int) intent.getSerializableExtra("selection");  //

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClosetManager cm = new ClosetManager(closet);

        //Initialize GridView using GridAdapter
        String[] clothesNames = cm.getClothesNames();
        int[] imgs = cm.getClothesImgs();
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), clothesNames, imgs);
        binding.gridView.setAdapter(gridAdapter);

        //Navigation Bar
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_clothes);
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
                        Intent i2 = new Intent(getApplicationContext(), OutfitListActivity.class);
                        i2.putExtra("db", database);
                        i2.putExtra("aID", aID);
                        i2.putExtra("cID", cID);
                        startActivity(i2);
                        overridePendingTransition(0,0);
                        return true;
                    //Go to ClosetActivity
                    case R.id.navigation_clothes:
                        return true;
                }
                return false;
            }
        });
        if(selection!=-1){
            binding.bottomNavigation.setVisibility(View.GONE);
            binding.addItemButton.setVisibility(View.GONE);
        }

        //Clicking an item in the grid
        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selection!=-1){
                    ClothesItem item = closet.getClothesItems().get(position);
                    Outfit outfit = closet.getOutfits().get(selection);
                    if(outfit.addClothesItem(item)){
                        Toast.makeText(ClosetActivity.this, "Added New clothes to a Outfit", Toast.LENGTH_SHORT).show();
                    };

//                    Intent i2 = new Intent(getApplicationContext(), OutfitListActivity.class);
//                    i2.putExtra("db", database);
//                    i2.putExtra("aID", aID);
//                    i2.putExtra("cID", cID);
//                    startActivity(i2);
                    //going back to OutfitItem page.
                    Intent i3 = new Intent(getApplicationContext(), OutfitItemActivity.class);
                    i3.putExtra("db", database);
                    i3.putExtra("aID", aID);
                    i3.putExtra("cID", cID);
                    i3.putExtra("curr", selection);
                    i3.putExtra("tab", 0);
                    startActivity(i3);
                    overridePendingTransition(0,0);
                }else{
                    //Go to DetailActivity
                    Intent i3 = new Intent(getApplicationContext(), DetailActivity.class);
                    i3.putExtra("db", database);
                    i3.putExtra("aID", aID);
                    i3.putExtra("cID", cID);
                    i3.putExtra("curr", position);
                    i3.putExtra("tab", 1);
                    startActivity(i3);
                }

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
    }
}
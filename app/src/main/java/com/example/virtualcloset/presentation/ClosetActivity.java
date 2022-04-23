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
import android.widget.Toast;

import com.example.virtualcloset.objects.Closet;
import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapterCloset;
import com.example.virtualcloset.storage.Database;
import com.example.virtualcloset.databinding.ActivityClosetBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

// The ClosetActivity class displays the the clothing items in the closet
public class ClosetActivity extends AppCompatActivity {

    ActivityClosetBinding binding;
    GridAdapterCloset gridAdapter;
    int selection;

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
        if (intent.getExtras().containsKey("selection")) {
            selection = (int) intent.getSerializableExtra("selection");
        } else {
            selection = -1;
        }

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClosetManager cm = new ClosetManager(closet);

        //Initialize GridView using GridAdapterCloset
        gridAdapter = new GridAdapterCloset(getApplicationContext(), cm);
        binding.gridView.setAdapter(gridAdapter);

        //Set Up Tag Selection Dropdown
        Spinner tagFilter = findViewById(R.id.tagFilter);
        List<String> allUniqueTags = cm.getAllTags(closet.getClothesItems());
        allUniqueTags.add(0, "All Clothing");
        allUniqueTags.add(1, "Favourites");
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
        //set the visibility of Navigation and addButton to GONE
        if (selection != -1) {
            binding.bottomNavigation.setVisibility(View.GONE);
            binding.addItemButton.setVisibility(View.GONE);
        }

        //Clicking an item in the grid
        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selection!=-1){ //If coming from outfit already!
                    //Select item to add to an outfit then navigate back to OutfitItemActivity
                    UserAccount account = database.getAccounts().get(aID);
                    Closet closet = account.getClosets().get(cID);
                    ClothesItem item = closet.getClothesItems().get(position);
                    Outfit outfit = closet.getOutfits().get(selection);
                    if (outfit.addClothesItem(item)) {
                        //outfit.setImg(item.getImg());
                        Toast.makeText(ClosetActivity.this, "Add New clothes to the Outfit", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ClosetActivity.this, "This clothes has already been added", Toast.LENGTH_SHORT).show();
                    }
                    //going back to OutfitItem page.
                    Intent i2 = new Intent(getApplicationContext(), OutfitItemActivity.class);
                    i2.putExtra("db", database);
                    i2.putExtra("aID", aID);
                    i2.putExtra("cID", cID);
                    i2.putExtra("curr", selection);
                    i2.putExtra("tab", 0);
                    startActivity(i2);
                } else {
                    //Go to DetailActivity for the selected item
                    Intent i3 = new Intent(getApplicationContext(), DetailActivity.class);
                    int currID = gridAdapter.getItemIDByPosition(position);
                    i3.putExtra("db", database);
                    i3.putExtra("aID", aID);
                    i3.putExtra("cID", cID);
                    i3.putExtra("iID", currID);
                    i3.putExtra("tab", 1);
                    startActivity(i3);
                }
            }
        });

        //Clicking the add item button
        //Navigates to the AddClothingActivity
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

        //Selecting and option from the dropdown list
        //Gets the tag the user selected from the list
        // and tells gridAdapter to filter clothing items by that tag
        binding.tagFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                       long l) {
                String filter = adapterView.getItemAtPosition(position).toString();
                gridAdapter.setFilter(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
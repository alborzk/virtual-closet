package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityOutfitListBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.storage.Database;

import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//Displays all the outfits in a closet (groups of clothing items)
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

        //Get Other UI Widgets
        final FloatingActionButton outfitDeleteButton = findViewById(R.id.outfit_delete_button);
        final FloatingActionButton outfitAddButton = findViewById(R.id.outfit_add_button);
        final Button outfitAddOne = (Button) findViewById(R.id.add_one_outfit);
        final Button outfitBackButton = (Button) findViewById(R.id.outfit_backButton);
        final EditText editTags = (EditText) findViewById(R.id.editOutfit);
        //Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_outfits);
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
                        return true;
                    //Go to ClosetActivity
                    case R.id.navigation_clothes:
                        Intent i2 = new Intent(getApplicationContext(), ClosetActivity.class);
                        i2.putExtra("db", database);
                        i2.putExtra("aID", aID);
                        i2.putExtra("cID", cID);
                        i2.putExtra("selection",-1);
                        startActivity(i2);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        //Click on an item in the Grid
        clickOnGrid( database,  aID, cID );

        //Click on addButton(+) on list page
        //Shows the editing elements to create a new outfit
        binding.outfitAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outfitDeleteButton.setVisibility(View.GONE);
                outfitAddButton.setVisibility(View.GONE);
                outfitAddOne.setVisibility(View.VISIBLE);
                editTags.setVisibility(View.VISIBLE);
                outfitBackButton.setVisibility(View.VISIBLE);

            }
        });
        //Click on add_one_outfit while in add mode
        //Creates a new Outfit by getting the name from user input to text box
        binding.addOneOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String outfitName = editTags.getText().toString();

                if (outfitName.trim().length() > 0) {
                    //=================================================================
                    //data base involved here
                    //=================================================================
                    Outfit newOutfit = new Outfit(closet.getNumOutfits() + 1, outfitName);
                    closet.getOutfits().add(newOutfit);
                    editTags.setText("");
                    GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), cm.getOutfitsNames(), cm.getOutfitsImgs());
                    binding.gridOutfitList.setAdapter(gridAdapter);
                    Toast.makeText(OutfitListActivity.this, "Added New Outfit: " + outfitName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OutfitListActivity.this, "Couldn't add New Outfit, input is empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //Click on removeButton (-)
        //Turns delete mode on and allows removing an outfit
        final int[] delete = {0}; //determine if in delete mode.
        binding.outfitDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outfitDeleteButton.setVisibility(View.GONE);
                outfitAddButton.setVisibility(View.GONE);
                outfitBackButton.setVisibility(View.VISIBLE);
                Toast.makeText(OutfitListActivity.this, "DELETE MODE ON!", Toast.LENGTH_SHORT).show();
                delete[0] =1;
                //delete the item selected on grid
                binding.gridOutfitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //need to update here.
                        closet.getOutfits().remove(position);
                        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), cm.getOutfitsNames(), cm.getOutfitsImgs());
                        binding.gridOutfitList.setAdapter(gridAdapter);
                    }
                });


            }
        });
        //Click on backButton while in add mode.
        //Hides the editing elements and shuts off delete mode
        binding.outfitBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outfitDeleteButton.setVisibility(View.VISIBLE);
                outfitAddButton.setVisibility(View.VISIBLE);
                outfitAddOne.setVisibility(View.GONE);
                editTags.setVisibility(View.GONE);
                outfitBackButton.setVisibility(View.GONE);

                if(delete[0]==1){
                    Toast.makeText(OutfitListActivity.this, "DELETE MODE OFF!", Toast.LENGTH_SHORT).show();
                    //set listener back to default action
                    clickOnGrid( database,  aID, cID );
                    delete[0]=0;
                }

            }
        });


    }
    //Click on an outfit in the grid
    //Navigate to OutfitItemActivity
    void clickOnGrid(Database database, int aID,int cID ){
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
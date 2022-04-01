package com.example.virtualcloset.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityAccountBinding;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.storage.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    ActivityAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);

        //Get Variables from Account
        String name = account.getUsername();
        int numClothes = closet.getNumClothes();
        int numOutfits = closet.getNumOutfits();
        int img = R.drawable.user_icon;

        //Set Up Name Display
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.nameDisplay);
        nameDisplay.setText(name);

        //Set Up Image Display
        ImageView imgDisplay = (ImageView) binding.getRoot().findViewById(R.id.imgDisplay);
        imgDisplay.setImageResource(img);

        //Set Up numClothes Display
        TextView clothes = (TextView) binding.getRoot().findViewById(R.id.numClothes);
        clothes.setText("Clothes: " + numClothes);

        //Set Up numOutfits Display
        TextView outfits = (TextView) binding.getRoot().findViewById(R.id.numOutfits);
        outfits.setText("Outfits: " + numOutfits);

        //Click "Sign Out" Button
        binding.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to LoginActivity
                Intent i1 = new Intent(getApplicationContext(), LoginActivity.class);
                i1.putExtra("db", database);
                startActivity(i1);
            }
        });

        //Navigation Bar
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_accounts);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    //Go to LoginActivity
                    case R.id.navigation_accounts:
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
                        Intent i3 = new Intent(getApplicationContext(), ClosetActivity.class);
                        i3.putExtra("db", database);
                        i3.putExtra("aID", aID);
                        i3.putExtra("cID", cID);
                        startActivity(i3);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}
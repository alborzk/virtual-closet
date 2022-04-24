package com.example.virtualcloset.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.objects.Closet;
import com.example.virtualcloset.R;
import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Outfit;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.databinding.ActivityAccountBinding;
import com.example.virtualcloset.storage.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

// The AccountActivity class displays details about the user's account
public class AccountActivity extends AppCompatActivity {

    ActivityAccountBinding binding;
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        DataManager dm = new DataManager(database);

        //Get Variables from Account
        String name = account.getUsername();
        int numClothes = closet.getNumClothes();
        int numOutfits = closet.getNumOutfits();
        int img = account.getImg();

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

        //Click Avatar Button
        binding.imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to EditAccountActivity
                Intent i4 = new Intent(getApplicationContext(), EditAccountActivity.class);
                i4.putExtra("db", database);
                i4.putExtra("aID", aID);
                i4.putExtra("cID", cID);
                startActivity(i4);
            }
        });

        //Click Change Username Button
        binding.usernameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                builder.setTitle("Enter your new username:");
                m_Text = "";
                // Set up the input
                final EditText input = new EditText(AccountActivity.this);
                // Specify the type of input expected
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setContentDescription("user name");
                builder.setView(input);
                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        //Check if account already exists
                        boolean accountExists = dm.accountExists(m_Text);

                        //If user already exists, can't change username
                        if (accountExists) {
                            Toast.makeText(getApplicationContext(), "Changes not saved! An account with that username already exists.", Toast.LENGTH_SHORT).show();
                        }
                        //If username invalid, can't change username
                        else if (m_Text.length() < 3) {
                            Toast.makeText(getApplicationContext(), "Changes not saved! New username must be at least 3 characters.", Toast.LENGTH_SHORT).show();
                        }
                        //If username unique and valid, change username to it
                        else {
                            Toast.makeText(getApplicationContext(), "Changes saved!", Toast.LENGTH_SHORT).show();
                            account.setUsername(m_Text);
                            nameDisplay.setText(m_Text);
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        //Click Change Password Button
        binding.passwordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                builder.setTitle("Enter your new password:");
                m_Text = "";
                // Set up the input
                final EditText input = new EditText(AccountActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input.setContentDescription("password");
                builder.setView(input);
                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();

                        //If password invalid, cant change it
                        if (m_Text.length() < 4) {
                            Toast.makeText(getApplicationContext(), "Changes not saved! New password must be at least 4 characters.", Toast.LENGTH_SHORT).show();
                        }
                        //If password valid, change it
                        else {
                            Toast.makeText(getApplicationContext(), "Changes saved!", Toast.LENGTH_SHORT).show();
                            account.setPassword(m_Text);
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
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
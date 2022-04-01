package com.example.virtualcloset.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityLoginBinding;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.storage.Database;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Set up database
        Database database;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //If database is passed to us, receive it, otherwise initialize it
        if (extras != null) {
            if (extras.containsKey("db")) {
                database = (Database) extras.getSerializable("db");
            }
            else{
                database = initializeDatabase();
            }
        }
        else{
            database = initializeDatabase();
        }
        DataManager dm = new DataManager(database);

        //Set up UI widgets
        TextView userTV = (TextView) binding.getRoot().findViewById(R.id.editTextTextEmailAddress);
        TextView passTV = (TextView) binding.getRoot().findViewById(R.id.editTextTextPassword);

        //Click "Log In"
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check input values
                String userInput = userTV.getText().toString();
                String passInput = passTV.getText().toString();

                //Find account in database
                UserAccount account = dm.findAccount(userInput, passInput);

                //If account exists, log in using it
                if (account != null) {
                    Toast.makeText(getApplicationContext(), "Welcome back, " + account.getUsername() + "! Loading your closet...", Toast.LENGTH_SHORT).show();
                    int aID = dm.findAID(userInput,passInput);
                    //Go to ClosetActivity
                    Intent i1 = new Intent(getApplicationContext(), ClosetActivity.class);
                    i1.putExtra("aID", aID);
                    i1.putExtra("cID", 0);
                    i1.putExtra("db", database);
                    startActivity(i1);
                }
                //If account doesn't exist, can't log in
                else {
                    Toast.makeText(getApplicationContext(), "Log in failed! User/password combination not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Click "Sign Up"
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check input values
                String userInput = userTV.getText().toString();
                String passInput = passTV.getText().toString();

                //Check if account already exists
                boolean accountExists = dm.accountExists(userInput);

                //If user already exists, can't sign up
                if (accountExists) {
                    Toast.makeText(getApplicationContext(), "Sign up failed! An account with that username already exists.", Toast.LENGTH_SHORT).show();
                }
                //If username invalid, can't sign up
                else if (userInput.length() < 3) {
                    Toast.makeText(getApplicationContext(), "Sign up failed! Username must be at least 3 characters.", Toast.LENGTH_SHORT).show();
                }
                //If password invalid, can't sign up
                else if (passInput.length() < 4) {
                    Toast.makeText(getApplicationContext(), "Sign up failed! Password must be at least 4 characters.", Toast.LENGTH_SHORT).show();
                }
                //If username/password unique and valid, sign up using it
                else {
                    Toast.makeText(getApplicationContext(), "Welcome to your new closet, " + userInput + "! Click '+' to begin adding items.", Toast.LENGTH_SHORT).show();
                    UserAccount newAccount = new UserAccount(userInput, passInput, "user@email.com");
                    database.addAccount(newAccount);
                    Closet newCloset = new Closet(0, new ArrayList<ClothesItem>(), new ArrayList<Outfit>());
                    newAccount.addCloset(newCloset);
                    int aID = dm.findAID(userInput,passInput);

                    //Go to ClosetActivity
                    Intent i2 = new Intent(getApplicationContext(), ClosetActivity.class);
                    i2.putExtra("aID", aID);
                    i2.putExtra("cID", 0);
                    i2.putExtra("db", database);
                    startActivity(i2);

                }
            }
        });

        //Click "Info" to see default account login
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Login using default values 'user' and 'password'", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    //Initializes a new database, with a default user account
    private Database initializeDatabase() {
        Database database = new Database(true);

        //Create a new default account
        UserAccount defaultAccount = new UserAccount("user", "password", "user@email.com");
        database.addAccount(defaultAccount);

        //Set up default account with a closet
        Closet defaultCloset = new Closet(0, new ArrayList<ClothesItem>(), new ArrayList<Outfit>());
        defaultCloset.initializeDefaultCloset();
        defaultAccount.addCloset(defaultCloset);

        return database;
    }
}
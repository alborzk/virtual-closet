package com.example.virtualcloset.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.FragmentLoginBinding;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

public class
LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    Database database = new Database();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up UI widgets
        TextView userTV = (TextView) getView().findViewById(R.id.editTextTextEmailAddress);
        TextView passTV = (TextView) getView().findViewById(R.id.editTextTextPassword);

        //Initialize database
        Database database = new Database();
        database.initializeDefaultAccount();

//        //Get default username and password values
//        String user = database.getAccounts().get(0).getUsername();
//        String pass = database.getAccounts().get(0).getPassword();

        ArrayList<UserAccount> accounts = database.getAccounts();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check input values
                String userInput = userTV.getText().toString();
                String passInput = passTV.getText().toString();

                UserAccount account = findAccount(userInput, passInput, accounts);

                //If account exists, log in using it
                if (account != null) {
                    Toast.makeText(getContext().getApplicationContext(), "Welcome back, " + account.getUsername() + "! Loading your closet...", Toast.LENGTH_SHORT).show();
//                  NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_FirstFragment_to_outfitListActivity);

                    //Go to ClosetActivity
                    Intent intent = new Intent(getActivity(), ClosetActivity.class);
                    intent.putExtra("acc", account);
                    intent.putExtra("closet", account.getClosets().get(0));
                    intent.putExtra("db", database); //Database passed should be specific to the user, perhaps instead of passing database we pass userAccount?
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "Log in failed! User/password combination not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Check input values
            String userInput = userTV.getText().toString();
            String passInput = passTV.getText().toString();

            UserAccount account = findAccount(userInput, accounts);

            //If account exists, log in using it
            if (account != null){
                Toast.makeText(getContext().getApplicationContext(), "Sign up failed! An account with that username already exists.",Toast.LENGTH_SHORT).show();
//              NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_FirstFragment_to_outfitListActivity);
            }
            else{
                Toast.makeText(getContext().getApplicationContext(), "Welcome to your new closet, " + userInput + "! Click '+' to begin adding items.",Toast.LENGTH_SHORT).show();
                UserAccount newAccount = new UserAccount(userInput, passInput, "user@email.com");
                Closet newCloset = new Closet(0, new ArrayList<ClothesItem>(), new ArrayList<Outfit>());
                newAccount.addCloset(newCloset);
                database.getAccounts().add(newAccount);

                Intent intent = new Intent(getActivity(), ClosetActivity.class);
                intent.putExtra("acc", newAccount);
                intent.putExtra("closet", newCloset);
                intent.putExtra("db", database); //Database passed should be specific to the user, perhaps instead of passing database we pass userAccount?
                startActivity(intent);

            }
        }
    });
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public UserAccount findAccount(String userInput, String passInput, ArrayList<UserAccount> accounts){
        UserAccount account = null;
        boolean accountFound = false;
        for (int i = 0; i < accounts.size() && !accountFound; i++) {
            UserAccount curr = accounts.get(i);
            if ((curr.getUsername().equals(userInput) || curr.getEmail().equals(userInput)) && curr.getPassword().equals(passInput)) {
                accountFound = true;
                account = curr;
            }
        }
        return account;
    }

    public UserAccount findAccount(String userInput, ArrayList<UserAccount> accounts){
        UserAccount account = null;
        boolean accountFound = false;
        for (int i = 0; i < accounts.size() && !accountFound; i++) {
            UserAccount curr = accounts.get(i);
            if ((curr.getUsername().equals(userInput) || curr.getEmail().equals(userInput))) {
                accountFound = true;
                account = curr;
            }
        }
        return account;
    }


}
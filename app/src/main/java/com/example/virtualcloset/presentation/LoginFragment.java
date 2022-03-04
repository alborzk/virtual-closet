package com.example.virtualcloset.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.virtualcloset.R;
import com.example.virtualcloset.databinding.FragmentLoginBinding;
import com.example.virtualcloset.storage.Database;

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

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    TextView userInput = (TextView) getView().findViewById(R.id.editTextTextEmailAddress);
                    TextView passInput = (TextView) getView().findViewById(R.id.editTextTextPassword);
                    String user = database.getAccounts().get(0).getUsername();
                    String pass = database.getAccounts().get(0).getPassword();

                    TextView msg = getView().findViewById(R.id.textview_first);
                    if(userInput.getText().toString().equals(user) && passInput.getText().toString().equals(pass)){
                        msg.setText("Log In Success!");
                        NavHostFragment.findNavController(LoginFragment.this)
                                .navigate(R.id.action_FirstFragment_to_closetActivity);
                    }
                    else{
                        msg.setText("Log In Failed! Incorrect Username or Password");
                    }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
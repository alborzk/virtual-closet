package com.example.virtualcloset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.virtualcloset.databinding.FragmentFirstBinding;

public class
FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    TextView username = (TextView) getView().findViewById(R.id.editTextTextEmailAddress);
                    TextView password = (TextView) getView().findViewById(R.id.editTextTextPassword);
                    TextView msg = getView().findViewById(R.id.textview_first);
                    if(username.getText().toString().equals("user") && password.getText().toString().equals("password")){
                        //Need to check username/password against database.
                        //Need to link to other fragment.
                        msg.setText("Log In Success!");
                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_closetActivity);
                    }
                    else{
                        msg.setText("Log In Failed! Incorrect Username or Password");
                    }
            }

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_closetActivity);
//                }
//            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityEditAccountBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.storage.Database;

public class EditAccountActivity extends AppCompatActivity {

    ActivityEditAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);

        //Initialize GridView using GridAdapter
        String[] avatars = {"Default", "Avatar 1", "Avatar 2", "Avatar 3", "Avatar 4", "Avatar 5", "Avatar 6", "Avatar 7", "Avatar 8"};
        int[] imgs = {R.drawable.user_icon, R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3, R.drawable.avatar_4, R.drawable.avatar_5, R.drawable.avatar_6, R.drawable.avatar_7, R.drawable.avatar_8};
        GridAdapter gridAdapter = new GridAdapter(EditAccountActivity.this, avatars, imgs);
        binding.avatarGrid.setAdapter(gridAdapter);

        //Clicking an item in the grid
        binding.avatarGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set account avatar to selected image
                account.setImg(imgs[position]);
                //Return to DetailActivity
                Intent i1 = new Intent(getApplicationContext(), AccountActivity.class);
                i1.putExtra("db", database);
                i1.putExtra("aID", aID);
                i1.putExtra("cID", cID);
                startActivity(i1);
            }
        });
    }
}
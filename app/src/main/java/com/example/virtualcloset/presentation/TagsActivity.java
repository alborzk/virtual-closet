package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityTagsBinding;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.storage.Database;

public class TagsActivity extends AppCompatActivity {
    ActivityTagsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTagsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        UserAccount account = (UserAccount) intent.getSerializableExtra("acc");
        Closet closet = (Closet) intent.getSerializableExtra("closet");
        ClothesItem curr = (ClothesItem) intent.getSerializableExtra("curr");

        //display the name of the item
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.nameDisplay);
        nameDisplay.setText(curr.getName());

        //setting up grid of tags

        //add new tag
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameInput = (EditText) binding.getRoot().findViewById(R.id.addTagName);
                String name = nameInput.getText().toString();
//                EditText typeInput = (EditText) binding.getRoot().findViewById(R.id.addTagType);
//                String type = typeInput.getText().toString();

                Integer currId = curr.getId();
                Integer tagId = curr.getTags().size()-1;
                String newIdString = currId.toString() + tagId.toString();
                Integer newId = Integer.parseInt(newIdString);
                closet.addTag(curr.getId(), new Tag(newId, name));
                Toast.makeText(TagsActivity.this, "Added \"" + name + "\" Tag", Toast.LENGTH_SHORT).show();
            }
        });

        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("db", database);
                intent.putExtra("acc", account);
                intent.putExtra("closet", closet);
                intent.putExtra("curr", curr);
                startActivity(intent);
            }
        });
    }
}
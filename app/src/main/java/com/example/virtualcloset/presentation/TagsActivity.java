package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.databinding.ActivityTagsBinding;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.storage.Database;

public class TagsActivity extends AppCompatActivity {
    ActivityTagsBinding binding;
    private ClothesItem item;
    private String bName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTagsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int index = intent.getExtras().getInt("index");
        item = database.getClothesItems().get(index);

        //display the name of the item
        bName = item.getName();
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.nameDisplay);
        nameDisplay.setText(bName);

        //setting up grid of tags

        //add new tag
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameInput = (EditText) binding.getRoot().findViewById(R.id.addTagName);
                String name = nameInput.getText().toString();
                EditText typeInput = (EditText) binding.getRoot().findViewById(R.id.addTagType);
                String type = typeInput.getText().toString();

                database.getClothesItems().get(index).addTag(new Tag(name, type));
                Toast.makeText(TagsActivity.this, "Added \"" + name + "\" Tag", Toast.LENGTH_SHORT).show();
            }
        });

        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("db", database);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });
    }
}
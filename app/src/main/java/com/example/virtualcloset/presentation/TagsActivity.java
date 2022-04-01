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

import java.util.ArrayList;


public class TagsActivity extends AppCompatActivity {

    ActivityTagsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTagsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");
        int curr = (int) intent.getSerializableExtra("curr");
        int tab = (int) intent.getSerializableExtra("tab");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClothesItem item = closet.getClothesItems().get(curr);

        //Display Item Name
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.nameDisplay);
        nameDisplay.setText(item.getName());

        //Click "Add" Button
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Tag Name
                EditText tagInput = (EditText) binding.getRoot().findViewById(R.id.addTagName);
                String tag = tagInput.getText().toString();

                //Get Tag ID
                Integer currId = item.getId();
                Integer tagId = item.getTags().size();
                String newIdString = currId.toString() + tagId;
                Integer newId = Integer.parseInt(newIdString);

                //Add Tag to Item
                item.addTag(new Tag(newId, tag));
                ArrayList<Tag> tags = item.getTags();
                for (Tag t : tags){
                    System.out.print(t.getId());
                    System.out.println(t.getName());
                }
                Toast.makeText(TagsActivity.this, "Added \"" + tag + "\" Tag", Toast.LENGTH_SHORT).show();
            }
        });

        //Click "Done" Button
        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), DetailActivity.class);
                i1.putExtra("db", database);
                i1.putExtra("aID", aID);
                i1.putExtra("cID", cID);
                i1.putExtra("curr", curr);
                i1.putExtra("tab", tab);
                startActivity(i1);
            }
        });
    }
}
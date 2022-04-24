package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.objects.Closet;
import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.objects.Tag;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.databinding.ActivityAddClothingBinding;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

// Displays the interface for adding a new item of clothing to the closet
public class AddClothingActivity extends AppCompatActivity {

    ActivityAddClothingBinding binding;
    int SELECT_PICTURE = 200;
    ArrayList<Tag> tags;
    int numTags;
    String tagsString;
    int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClothingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database db = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");
        if (intent.getExtras().containsKey("img")) {
            img = (int) intent.getSerializableExtra("img");  //
        }
        else{
            img = R.drawable.add_image;
        }

        //Get Objects from IDs
        UserAccount account = db.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);

        //Set Up UI Widgets
        ImageView imgDisplay = (ImageView) binding.getRoot().findViewById(R.id.addImgDisplay);
        imgDisplay.setImageResource(img);
        TextView tagsDisplay = (TextView) binding.getRoot().findViewById(R.id.tagDisplay);
        EditText nameInput = (EditText) binding.getRoot().findViewById(R.id.editTextAdd);
        EditText editTags = (EditText) binding.getRoot().findViewById(R.id.editTags);
        numTags = 0;
        tagsString = "";
        tags = new ArrayList<Tag>();

        //Click "Upload Image"
        binding.addImgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Image Chooser
                Intent i2 = new Intent(AddClothingActivity.this, AddImageActivity.class);
                i2.putExtra("db", db);
                i2.putExtra("aID", aID);
                i2.putExtra("cID", cID);
                i2.putExtra("mode",1);
                startActivity(i2);
            }
        });

        //Click "Add" Button While Editing Tags
        binding.addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Tag Name
                String tag = editTags.getText().toString();

                //Get Tag ID
                Integer currId = closet.getNumClothes();
                String newIdString = currId.toString() + numTags;
                Integer newId = Integer.parseInt(newIdString);

                //Add Tag to Item
                if (tag.trim().length() > 0){
                    tags.add(new Tag(newId, tag));
                    tagsString = tagsString + tag + "  |  ";
                    tagsDisplay.setText(tagsString);
                    numTags++;
                    editTags.setText("");
                    Toast.makeText(AddClothingActivity.this, "Added \"" + tag + "\" Tag", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddClothingActivity.this, "Couldn't add tag, input is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Click "Done" to navigate back to ClosetActivity
        binding.doneAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new item to closet
                String name = nameInput.getText().toString();
                ClothesItem newItem = new ClothesItem(closet.getNumClothes(), name, tags, img);
                closet.addClothesItem(newItem);

                //Go to ClosetActivity
                Intent i1 = new Intent(AddClothingActivity.this, ClosetActivity.class);
                i1.putExtra("db", db);
                i1.putExtra("aID", aID);
                i1.putExtra("cID", cID);
                startActivity(i1);
            }
        });
    }
}
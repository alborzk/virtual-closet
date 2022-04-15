package com.example.virtualcloset.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.virtualcloset.Closet;

import androidx.appcompat.app.AppCompatActivity;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityDetailBinding;
import com.example.virtualcloset.storage.Database;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private String name;
    private String tags;
    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
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

        //Initialize Variables from Current Item
        name = item.getName();
        tags = item.getTagsString();
        img = item.getImg();

        //Set Up Name Display
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.nameDisplay);
        nameDisplay.setText(name);

        //Set Up Tags Display
        TextView tagDisplay = (TextView) binding.getRoot().findViewById(R.id.tagDisplay);
        tagDisplay.setText(tags);

        //Set Up Image Display
        ImageView imgDisplay = (ImageView) binding.getRoot().findViewById(R.id.imgDisplay);
        imgDisplay.setImageResource(img);

        //Set Up Tag Dropdown
        Spinner tagSpinner = findViewById(R.id.tagSpinner);
        List<String> tagNames = item.getTagNames();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tagNames);
        tagSpinner.setAdapter(adapter);

        //Get Other UI Widgets
        Button doneButton = (Button) binding.getRoot().findViewById(R.id.doneButton);
        Button backButton = (Button) binding.getRoot().findViewById(R.id.backButton);
        Button editButton = (Button) binding.getRoot().findViewById(R.id.editButton);
        Button addButton = (Button) binding.getRoot().findViewById(R.id.addButton);
        Button removeButton = (Button) binding.getRoot().findViewById(R.id.removeButton);
        Button removeTagButton = (Button) binding.getRoot().findViewById(R.id.removeTagButton);
        EditText editTags = (EditText) binding.getRoot().findViewById(R.id.editTags);

        //Check if already a favourite and make heart filled
        if(item.isFave()){
            binding.favourite.setChecked(true);
            binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
        } else {
            binding.favourite.setChecked(false);
            binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
        }

        //Click "Done" Button
        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Determine which tab we came from
                Class dest;
                if (tab == 0){
                    dest = OutfitListActivity.class;
                }
                else{
                    dest = ClosetActivity.class;
                }
                //Go to Activity
                Intent i1 = new Intent(getApplicationContext(), dest);
                i1.putExtra("db", database);
                i1.putExtra("aID", aID);
                i1.putExtra("cID", cID);
                startActivity(i1);
            }
        });

        //Click "Favourite" Button
        binding.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.favourite.isChecked()){
                    item.favorite();
                    Toast.makeText(DetailActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                    binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
                } else {
                    item.unFavorite();
                    Toast.makeText(DetailActivity.this, "Removed from Favourites", Toast.LENGTH_SHORT).show();
                    binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
                }
            }
        });

        //Click "Edit" Button
        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editButton.setVisibility(View.GONE);
                doneButton.setVisibility(View.GONE);
                tagSpinner.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);
                editTags.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);
                removeButton.setVisibility(View.VISIBLE);
                removeTagButton.setVisibility(View.VISIBLE);

//                Intent i2 = new Intent(getApplicationContext(), TagsActivity.class);
//                i2.putExtra("db", database);
//                i2.putExtra("aID", aID);
//                i2.putExtra("cID", cID);
//                i2.putExtra("curr", curr);
//                i2.putExtra("tab", tab);
//                startActivity(i2);
            }
        });

        //Click "Add" Button While Editing
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Tag Name
                String tag = editTags.getText().toString();

                //Get Tag IDuser
                Integer currId = item.getId();
                Integer tagId = item.getTags().size();
                String newIdString = currId.toString() + tagId;
                Integer newId = Integer.parseInt(newIdString);

                //Add Tag to Item
                if (tag.trim().length() > 0){
                    //if(item.findTagByName(tag) != null) {
                        item.addTag(new Tag(newId, tag));
                        tagDisplay.setText(item.getTagsString());
                        editTags.setText("");
                        Toast.makeText(DetailActivity.this, "Added \"" + tag + "\" Tag", Toast.LENGTH_SHORT).show();
                        //Update Spinner
                        tagNames.add(tag);
                        adapter.notifyDataSetChanged();
                    //}
                    //else{
                    //    Toast.makeText(DetailActivity.this, "Item already has the tag \"" + tag, Toast.LENGTH_SHORT).show();
                    //}
                }
                else{
                    Toast.makeText(DetailActivity.this, "Couldn't add tag, input is empty!", Toast.LENGTH_SHORT).show();
                }

//                editButton.setVisibility(View.VISIBLE);
//                doneButton.setVisibility(View.VISIBLE);
//                backButton.setVisibility(View.GONE);
//                editTags.setVisibility(View.GONE);
//                addButton.setVisibility(View.GONE);
            }
        });

        //Click "Remove Tag" Button While Editing Tags
        binding.removeTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Tag Name
                String rName = tagSpinner.getSelectedItem().toString();

                //Find The Tag And Remove It
                Tag rTag = item.findTagByName(rName);
                if (rTag != null){
                    item.removeTag(rTag);

                    //Update Interface
                    tagDisplay.setText(item.getTagsString());
                    tagSpinner.setSelection(0);
                    tagNames.remove(rName);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(DetailActivity.this, "Removed \"" + rTag.getName() + "\" Tag", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DetailActivity.this, "Couldn't find a tag with that name!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Click "Remove" Button While Editing
        binding.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Removed " + item.getName() + " from your closet", Toast.LENGTH_SHORT).show();

//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.setMessage("Are you sure you want to delete")
//                        .setTitle("Delete")
//                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // CANCEL
//                            }
//                        });
//                // Create the AlertDialog object and return it
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
                closet.removeClothesItem(item);
                Intent i2 = new Intent(getApplicationContext(), ClosetActivity.class);
                i2.putExtra("db", database);
                i2.putExtra("aID", aID);
                i2.putExtra("cID", cID);
                startActivity(i2);
            }
        });

        //Click "Back" Button While Editing Tags
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editButton.setVisibility(View.VISIBLE);
                doneButton.setVisibility(View.VISIBLE);
                tagSpinner.setVisibility(View.GONE);
                backButton.setVisibility(View.GONE);
                editTags.setVisibility(View.GONE);
                addButton.setVisibility(View.GONE);
                removeButton.setVisibility(View.GONE);
                removeTagButton.setVisibility(View.GONE);
            }
        });
    }
}
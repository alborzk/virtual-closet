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

// Displays a single item of clothing (image, name, tags)
// Can toggle to Edit mode to add/remove tags, delete the item
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
//        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");
        int iID = (int) intent.getSerializableExtra("iID");
        int tab = (int) intent.getSerializableExtra("tab");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClothesItem item = closet.getItem(iID);

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
        if(item.getFave()){
            binding.favourite.setChecked(true);
            binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
        } else {
            binding.favourite.setChecked(false);
            binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
        }

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

        //Click "Done" Button
        //Navigates back to the Outfit or Closet activity
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
                i1.putExtra("selection",-1);
                startActivity(i1);
            }
        });

        //Click "Edit" Button
        //Shows the editing elements to allow to modify tags or delete the item
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
            }
        });

        //Click "Back" Button While Editing Tags
        //Hides the editing elements to allow to modify tags or delete the item
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

        //Click "Add" Button While Editing
        //Gets string from user input into the editTags text box and adds to list of tags
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
                    if(item.findTagByName(tag) == null) {
                        item.addTag(new Tag(newId, tag));
                        tagDisplay.setText(item.getTagsString());
                        editTags.setText("");
                        Toast.makeText(DetailActivity.this, "Added \"" + tag + "\" Tag", Toast.LENGTH_SHORT).show();
                        //Update Spinner
                        tagNames.add(tag);
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(DetailActivity.this, "Item already has the tag \"" + tag + "\"", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(DetailActivity.this, "Couldn't add tag, input is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Click "Remove Tag" Button While Editing Tags (minus sign)
        //Gets string from user selection in tagSpinner and removes from list of tags
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

        //Click "Remove" Button While Editing (trashcan)
        //Removes the clothing item from the closet
        binding.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setCancelable(true);
                builder.setTitle("Remove Item");
                builder.setMessage("Are you sure you want to remove '" + item.getName() + "' from your closet?");

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closet.removeClothesItem(item);
                        Intent i2 = new Intent(DetailActivity.this, ClosetActivity.class);
                        i2.putExtra("db", database);
                        i2.putExtra("aID", aID);
                        i2.putExtra("cID", cID);
                        startActivity(i2);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
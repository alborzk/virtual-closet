package com.example.virtualcloset.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.virtualcloset.Closet;
import androidx.appcompat.app.AppCompatActivity;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityDetailBinding;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private ClothesItem item;
    private String bName;
//    private ArrayList<Tag> bTags;
//    private String bTags;
    private int bImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and Current Item
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");

        UserAccount account = (UserAccount) intent.getSerializableExtra("acc");
        Closet closet = (Closet) intent.getSerializableExtra("closet");
        ClothesItem curr = (ClothesItem) intent.getSerializableExtra("curr");

//        //Initialize Variables from Current Item
//        bName = curr.getName();
//        bTags = curr.getTagsString();
//        bImg = curr.getImg();

        int index = intent.getExtras().getInt("index");
        item = database.getClothesItems().get(index);

        bName = item.getName();
        bImg = item.getImg();
        //bTags = (ArrayList<Tag>) intent.getSerializableExtra("dm");
        DataManager dm = (DataManager) intent.getSerializableExtra("dm");
        String allTags = dm.getTags()[index];

        //Set Up Name Display
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.nameDisplay);
        nameDisplay.setText(bName);

        //Set Up Tags Display
        TextView tagDisplay = (TextView) binding.getRoot().findViewById(R.id.tagDisplay);
        tagDisplay.setText(allTags);

        //Set Up Image Display
        ImageView imgDisplay = (ImageView) binding.getRoot().findViewById(R.id.imgDisplay);
        imgDisplay.setImageResource(bImg);

        //Check if already a favourite and make heart filled
        if(database.getClothesItems().get(index).fave){
            binding.favourite.setChecked(true);
            binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
        } else {
            binding.favourite.setChecked(false);
            binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
        }

        //Clicking "Done" Button
        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to ClosetActivity
                Intent intent = new Intent(getApplicationContext(), ClosetActivity.class);
                intent.putExtra("db", database);
                intent.putExtra("acc", account);
                intent.putExtra("closet", closet);
                startActivity(intent);
            }
        });

        //Clicking "Favourite" Button
        binding.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.favourite.isChecked()){
                    //adding to favourites
                    database.getClothesItems().get(index).fave = true;
                    Toast.makeText(DetailActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                    binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
                } else {
                    //removing from favourites
                    database.getClothesItems().get(index).fave = false;
                    Toast.makeText(DetailActivity.this, "Removed from Favourites", Toast.LENGTH_SHORT).show();
                    binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
                }
            }
        });

        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TagsActivity.class);
                intent.putExtra("db", database);
                intent.putExtra("acc", account);
                intent.putExtra("closet", closet);
                intent.putExtra("dm", dm);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });
    }

}
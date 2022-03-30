package com.example.virtualcloset.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityDetailBinding;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private String bName;
    private String bTags;
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

        //Initialize Variables from Current Item
        bName = curr.getName();
        bTags = curr.getTagsString();
        bImg = curr.getImg();

        //Set Up Name Display
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.nameDisplay);
        nameDisplay.setText(bName);

        //Set Up Tags Display
        TextView tagDisplay = (TextView) binding.getRoot().findViewById(R.id.tagDisplay);
        tagDisplay.setText(bTags);

        //Set Up Image Display
        ImageView imgDisplay = (ImageView) binding.getRoot().findViewById(R.id.imgDisplay);
        imgDisplay.setImageResource(bImg);

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
                    Tag fave = new Tag("favourite", "favourite");
                    Toast.makeText(DetailActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                    binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
                } else {
                    //removing from favourites
                    Toast.makeText(DetailActivity.this, "Removed from Favourites", Toast.LENGTH_SHORT).show();
                    binding.favourite.setBackgroundDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
                }
            }
        });

//        binding.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(DetailActivity.this,"Clothing editing available in a later iteration!",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
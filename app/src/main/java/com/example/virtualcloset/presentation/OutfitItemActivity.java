package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.databinding.ActivityOutfitItemBinding;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.logic.OufitDataManager;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

public class OutfitItemActivity extends AppCompatActivity {

    ActivityOutfitItemBinding binding;
    private int oID;
    private String oName;
    private ArrayList<ClothesItem> clothesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        oID = intent.getExtras().getInt("outfitID");
        oName = intent.getExtras().getString("outfitName");


        String id = String.valueOf(oID + 1);
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.outfit_item_title);
        id = id + ":  " + oName;
        nameDisplay.setText(id);


        Database database = new Database();
        OufitDataManager dm = new OufitDataManager(database);
        ArrayList<ClothesItem> clothesList = dm.getClothesList(oID);
        String[] clothesNames = dm.getNames(clothesList);
        int[] imgs = dm.getImgs(clothesList);
        String[] allTags = dm.getTags(clothesList);

        GridAdapter gridAdapter = new GridAdapter(OutfitItemActivity.this, clothesNames, imgs);
        binding.gridview2.setAdapter(gridAdapter);

        final Button button = (Button) findViewById(R.id.done_outfit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(OutfitItemActivity.this, OutfitListActivity.class);
                startActivity(i);
            }
        });
        binding.gridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(ClosetActivity.this,"You clicked on "+ clothesNames[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OutfitItemActivity.this, DetailActivity.class);
                String name = clothesNames[position];
                intent.putExtra("clothingName", name);
                String tags = allTags[position];
                intent.putExtra("itemTags", tags);
                int img = imgs[position];
                intent.putExtra("itemImg", img);
                startActivity(intent);


            }
        });

    }

}
package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityOutfitItemBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.logic.OutfitDataManager;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

public class OutfitItemActivity extends AppCompatActivity {

    ActivityOutfitItemBinding binding;
    private String oName;
    private ArrayList<ClothesItem> clothesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");
        int curr = intent.getExtras().getInt("curr");
        int tab = (int) intent.getSerializableExtra("tab");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClosetManager cm = new ClosetManager(closet);
        Outfit outfit = closet.getOutfits().get(curr);
        clothesList = cm.getClothesList(curr);
        oName = outfit.getName();

        //Set Up UI Widgets
        final Button button = (Button) findViewById(R.id.done_outfit);
        TextView nameDisplay = (TextView) binding.getRoot().findViewById(R.id.outfit_item_title);

        //Display Outfit Number & Name
        String id = String.valueOf(curr + 1);
        id = id + ":  " + oName;
        nameDisplay.setText(id);

        //Initialize GridAdapter
        String[] clothesNames = cm.getClothesNames(clothesList);
        int[] imgs = cm.getClothesImgs(clothesList);
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), clothesNames, imgs);
        binding.gridview2.setAdapter(gridAdapter);

        //Click "Done" Button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), OutfitListActivity.class);
                i2.putExtra("db", database);
                i2.putExtra("aID", aID);
                i2.putExtra("cID", cID);
                startActivity(i2);
            }
        });

        //Click on a ClothesItem
        binding.gridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Find ID of Item
                int itemID = clothesList.get(position).getId();

                //Go to DetailActivity
                Intent i3 = new Intent(getApplicationContext(), DetailActivity.class);
                i3.putExtra("db", database);
                i3.putExtra("aID", aID);
                i3.putExtra("cID", cID);
                i3.putExtra("curr", itemID);
                i3.putExtra("tab", 0);
                startActivity(i3);
            }
        });

    }

}
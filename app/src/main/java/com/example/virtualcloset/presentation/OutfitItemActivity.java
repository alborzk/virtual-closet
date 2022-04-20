package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityOutfitItemBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapter;
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
        int aID = (int) intent.getSerializableExtra("aID"); //account
        int cID = (int) intent.getSerializableExtra("cID"); //closet Id
        int curr = intent.getExtras().getInt("curr");
        int tab = (int) intent.getSerializableExtra("tab");

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        Outfit outfit = closet.getOutfits().get(curr);
        ClosetManager cm = new ClosetManager(closet);
        clothesList = outfit.getClothesItems();
        oName = outfit.getName();

        //Set Up UI Widgets
        final Button doneButton = (Button) findViewById(R.id.done_outfit);
        final Button addButton = (Button) findViewById(R.id.outfit_additem);
        final Button deleteButton = (Button) findViewById(R.id.outfit_deleItem);
        final Button backButton =(Button) findViewById(R.id.outfitItem_back);
        backButton.setVisibility(View.GONE);
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
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), OutfitListActivity.class);
                i2.putExtra("db", database);
                i2.putExtra("aID", aID);
                i2.putExtra("cID", cID);
                startActivity(i2);
            }
        });
        //click "add" Button
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //switch to ClosetActivity page to select a clothes
                Intent i2 = new Intent(getApplicationContext(), ClosetActivity.class);
                i2.putExtra("db", database);
                i2.putExtra("aID", aID);
                i2.putExtra("cID", cID);
                i2.putExtra("selection",curr);
                startActivity(i2);
                overridePendingTransition(0, 0);
            }
        });

        //click "delete" Button
        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                addButton.setVisibility(View.GONE);
                doneButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                backButton.setVisibility(View.VISIBLE);
                Toast.makeText(OutfitItemActivity.this, "DELETE MODE ON!", Toast.LENGTH_SHORT).show();
                binding.gridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //delete clothes on that position
                        clothesList.remove(position);
                        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), cm.getClothesNames(clothesList),cm.getClothesImgs(clothesList));
                        binding.gridview2.setAdapter(gridAdapter);
                    }
                });

            }
        });
        //click on BackButton while deleting clothes
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButton.setVisibility(View.VISIBLE);
                doneButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.GONE);
                Toast.makeText(OutfitItemActivity.this, "DELETE MODE OFF!", Toast.LENGTH_SHORT).show();
                clickOnGrid( database, aID, cID);
            }
        });
        //Click on a ClothesItem
         clickOnGrid( database, aID, cID);

    }
    void clickOnGrid(Database database, int aID,int cID){
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
                i3.putExtra("iID", itemID);
                i3.putExtra("tab", 0);
                startActivity(i3);
            }
        });
    }

}
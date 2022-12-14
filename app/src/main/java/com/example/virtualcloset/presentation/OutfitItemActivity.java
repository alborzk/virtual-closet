package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.objects.Closet;
import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.databinding.ActivityOutfitItemBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

//Displays the items of clothing in an outfit
public class OutfitItemActivity extends AppCompatActivity {

    ActivityOutfitItemBinding binding;
    private String oName;
    private ArrayList<ClothesItem> clothesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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
//        String id = String.valueOf(curr + 1);
//        id = id + ":  " + oName;
        nameDisplay.setText(oName);

        //Initialize GridAdapter
        String[] clothesNames = cm.getClothesNames(clothesList);
        int[] imgs = cm.getClothesImgs(clothesList);
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), clothesNames, imgs);
        binding.gridview2.setAdapter(gridAdapter);

        //Click "Done" Button
        //Navigate back to OutfitListActivity
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), OutfitListActivity.class);
                i2.putExtra("db", database);
                i2.putExtra("aID", aID);
                i2.putExtra("cID", cID);
                startActivity(i2);
            }
        });
        //Click "add" Button
        //Switch to ClosetActivity page to select a clothing item to add to outfit
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), ClosetActivity.class);
                i2.putExtra("db", database);
                i2.putExtra("aID", aID);
                i2.putExtra("cID", cID);
                i2.putExtra("selection",curr);
                startActivity(i2);
                overridePendingTransition(0, 0);
            }
        });

        //Click "Remove" Button While Editing (trashcan)
        //Removes the clothing item from the closet
        binding.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setCancelable(true);
                builder.setTitle("Remove Item");
                builder.setMessage("Are you sure you want to remove '" + oName + "' from your closet?");

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closet.removeOutfit(outfit);
                        Intent i2 = new Intent(OutfitItemActivity.this, OutfitListActivity.class);
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

        //Click "delete" Button
        //Turns on delete mode and allows removing items of clothing from the outfit
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
        //Click on BackButton while deleting clothes
        //Turns delete mode off
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
    //Click on item of clothing
    //Navigate to DetailView for that clothing item
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
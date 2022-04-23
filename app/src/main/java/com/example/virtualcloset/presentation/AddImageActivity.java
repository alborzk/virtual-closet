package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.virtualcloset.objects.Closet;
import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.databinding.ActivityAddImageBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.storage.Database;

public class AddImageActivity extends AppCompatActivity {

    ActivityAddImageBinding binding;
    int iID;
    int tab;
    ClothesItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database database = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");
        int mode = (int) intent.getSerializableExtra("mode");
        if (mode == 0){
            iID = (int) intent.getSerializableExtra("iID");
            tab = (int) intent.getSerializableExtra("tab");
        }

        //Get Objects from IDs
        UserAccount account = database.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClosetManager cm = new ClosetManager(closet);
        if (mode == 0){
            item = closet.getClothesItems().get(iID);
        }

        //Initialize GridView using GridAdapter
        String[] clothesNames = {"T-Shirt 1", "T-Shirt 2",  "Shirt", "Coat", "Sweater", "Hoodie", "Jacket", "Shorts", "Pants", "Skirt", "Dress", "Sports Bra",
                "Underwear 1", "Underwear 2", "Tank Top", "Sweatshorts", "Sweatpants", "Robe", "Swimsuit", "Sleepwear 1", "Sleepwear 2", "Tie", "Socks", "Mitts", "Shoes",
                "Heels", "Sneakers", "Sandals", "Flip Flops", "Backpack", "Purse", "Wallet", "Toque", "Hat", "Cap"};
        int[] imgs = {R.drawable.clothing_mtee, R.drawable.clothing_ftee, R.drawable.clothing_shirt, R.drawable.clothing_coat, R.drawable.clothing_sweater, R.drawable.clothing_hoodie,
                R.drawable.clothing_jacket, R.drawable.clothing_shorts, R.drawable.clothing_jeans, R.drawable.clothing_skirt, R.drawable.clothing_dress, R.drawable.clothing_sportsbra,
                R.drawable.clothing_bikini, R.drawable.clothing_boxers, R.drawable.clothing_tank, R.drawable.clothing_sweatshorts, R.drawable.clothing_sweats, R.drawable.clothing_robe,
                R.drawable.clothing_swimsuit, R.drawable.clothing_fsleep, R.drawable.clothing_msleep, R.drawable.clothing_tie, R.drawable.clothing_socks, R.drawable.clothing_mitts,
                R.drawable.clothing_shoes, R.drawable.clothing_heels, R.drawable.clothing_sneakers, R.drawable.clothing_sandals, R.drawable.clothing_flipflops, R.drawable.clothing_backpack,
                R.drawable.clothing_purse, R.drawable.clothing_wallet, R.drawable.clothing_toque, R.drawable.clothing_hat, R.drawable.clothing_cap};
                GridAdapter gridAdapter = new GridAdapter(AddImageActivity.this, clothesNames, imgs);
        binding.addImageGrid.setAdapter(gridAdapter);

        //Clicking an item in the grid
        binding.addImageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Return to DetailActivity
                Intent i1;
                if (mode == 0){
                    i1 = new Intent(getApplicationContext(), DetailActivity.class);
                    i1.putExtra("iID", iID);
                    i1.putExtra("tab", tab);
                    //Set item image to selected image
                    item.setImg(imgs[position]);
                }
                else{
                    i1 = new Intent(getApplicationContext(), AddClothingActivity.class);
                    i1.putExtra("img", imgs[position]);
                }
                i1.putExtra("db", database);
                i1.putExtra("aID", aID);
                i1.putExtra("cID", cID);
                startActivity(i1);
        }
    });
    }
}
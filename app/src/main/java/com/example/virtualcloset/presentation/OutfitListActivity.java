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
import com.example.virtualcloset.databinding.ActivityOutfitListBinding;
import com.example.virtualcloset.logic.GridAdapter;
import com.example.virtualcloset.logic.GridAdapter2;
import com.example.virtualcloset.logic.OufitDataManager;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

public class OutfitListActivity extends AppCompatActivity {
    ActivityOutfitListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Database database = new Database();
        OufitDataManager dm = new OufitDataManager(database);

        int[] outfitID=dm.getID();
        String [] outfitName=dm.getOutfitName();

        GridAdapter2 gridAdapter2 = new GridAdapter2(OutfitListActivity.this, outfitName);
        binding.gridOutfitList.setAdapter(gridAdapter2);

//        final Button button = (Button) findViewById(R.id.outlist_button);
        binding.gridOutfitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(ClosetActivity.this,"You clicked on "+ clothesNames[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OutfitListActivity.this, OutfitItemActivity.class);
                int oid = outfitID[position];
                intent.putExtra("outfitID", oid);
                String name=outfitName[position];
                intent.putExtra("outfitName",name);
//
//                intent.putExtra("clothesList",clothesItems);

                startActivity(intent);

            }
        });
    }
}
package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.databinding.ActivityAddClothingBinding;
import com.example.virtualcloset.databinding.ActivityClosetBinding;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.storage.Database;

public class AddClothingActivity extends AppCompatActivity {

    ActivityAddClothingBinding binding;
    Intent intent = this.getIntent();
//    Database db = (Database) intent.getSerializableExtra("db");
    Database db = new Database();
//    DataManager dm = intent.getParcelableExtra("dm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClothingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.doneAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameInput = (TextView) findViewById(R.id.editTextAdd);
                ImageView imgInput = (ImageView) findViewById(R.id.addImgDisplay);
                imgInput.setTag(R.drawable.add_image);
                ClothesItem newItem = new ClothesItem(db.getClothesItems().size()+1, nameInput.getText().toString(), (Integer) imgInput.getTag());
                //add newItem to Closet
                Intent intent = new Intent(AddClothingActivity.this, ClosetActivity.class);
//                Database db = database;
//                intent.putExtra("db", (Parcelable) db);
//                DataManager dm2 = dm;
//                intent.putExtra("d2", (Parcelable) dm2);
                startActivity(intent);
            }
        });
    }
}
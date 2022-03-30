package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityAddClothingBinding;
import com.example.virtualcloset.databinding.ActivityClosetBinding;
import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.storage.Database;

import java.io.IOException;

public class AddClothingActivity extends AppCompatActivity {

    ActivityAddClothingBinding binding;
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClothingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive database
        Intent intent = this.getIntent();
        Database db = (Database) intent.getSerializableExtra("db");
        UserAccount account = (UserAccount) intent.getSerializableExtra("acc");
        Closet closet = (Closet) intent.getSerializableExtra("closet");

        //Set up UI widgets
        Button selectImg = findViewById(R.id.selectImageButton);
        ImageView imgInput = findViewById(R.id.addImgDisplay);
        TextView nameInput = (TextView) findViewById(R.id.editTextAdd);

        //Save image ID
        imgInput.setTag(R.drawable.add_image);

        //Click "Upload Image"
        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Image Chooser
                imageChooser();
            }
        });

        //Click "done" button
        binding.doneAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new item to database
                ClothesItem newItem = new ClothesItem(closet.getClothesItems().size()+1, nameInput.getText().toString(), (Integer) imgInput.getTag());
                closet.addClothesItem(newItem);
                //Go to ClosetActivity
                Intent intent = new Intent(AddClothingActivity.this, ClosetActivity.class);
                intent.putExtra("db", db);
                intent.putExtra("acc", account);
                intent.putExtra("closet", closet);
                startActivity(intent);
            }
        });
    }

    //Choose Image
    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    //Triggered after user selects an image
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    try {
                        ImageView imgInput = findViewById(R.id.addImgDisplay);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imgInput.setImageDrawable(d);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
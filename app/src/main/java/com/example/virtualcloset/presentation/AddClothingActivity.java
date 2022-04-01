package com.example.virtualcloset.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.databinding.ActivityAddClothingBinding;
import com.example.virtualcloset.logic.ClosetManager;
import com.example.virtualcloset.storage.Database;

import java.io.IOException;
import java.util.ArrayList;

public class AddClothingActivity extends AppCompatActivity {

    ActivityAddClothingBinding binding;
    int SELECT_PICTURE = 200;
    ArrayList<Tag> tags;
    int numTags;
    String tagsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClothingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Receive Database and IDs
        Intent intent = this.getIntent();
        Database db = (Database) intent.getSerializableExtra("db");
        int aID = (int) intent.getSerializableExtra("aID");
        int cID = (int) intent.getSerializableExtra("cID");

        //Get Objects from IDs
        UserAccount account = db.getAccounts().get(aID);
        Closet closet = account.getClosets().get(cID);
        ClosetManager cm = new ClosetManager(closet);

        //Set Up UI Widgets
//        Button selectImg = findViewById(R.id.selectImageButton);
        ImageView imgInput = (ImageView) binding.getRoot().findViewById(R.id.addImgDisplay);
        TextView tagsDisplay = (TextView) binding.getRoot().findViewById(R.id.tagDisplay);
        EditText nameInput = (EditText) binding.getRoot().findViewById(R.id.editTextAdd);
        Button addButton = (Button) binding.getRoot().findViewById(R.id.addTagButton);
        EditText editTags = (EditText) binding.getRoot().findViewById(R.id.editTags);
        numTags = 0;
        tagsString = "";
        tags = new ArrayList<Tag>();

        //Click "Upload Image"
//        selectImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Open Image Chooser
//                imageChooser();
//            }
//        });

        //Click "Add" Button While Editing Tags
        binding.addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Tag Name
                String tag = editTags.getText().toString();

                //Get Tag ID
                Integer currId = closet.getNumClothes();
                String newIdString = currId.toString() + numTags;
                Integer newId = Integer.parseInt(newIdString);

                //Add Tag to Item
                if (tag.trim().length() > 0){
                    tags.add(new Tag(newId, tag));
                    tagsString = tagsString + tag + "  |  ";
                    tagsDisplay.setText(tagsString);
                    numTags++;
                    editTags.setText("");
                    Toast.makeText(AddClothingActivity.this, "Added \"" + tag + "\" Tag", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddClothingActivity.this, "Couldn't add tag, input is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Click "Done"
        binding.doneAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new item to closet
                String name = nameInput.getText().toString();
                int img = R.drawable.add_image;
                cm.addClothesItem(name, tags, img);

                //Go to ClosetActivity
                Intent i1 = new Intent(AddClothingActivity.this, ClosetActivity.class);
                i1.putExtra("db", db);
                i1.putExtra("aID", aID);
                i1.putExtra("cID", cID);
                startActivity(i1);
            }
        });
    }

//    //Choose Image
//    void imageChooser() {
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//        // pass the constant to compare it with the returned requestCode
//        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
//    }
//
//    //Triggered after user selects an image
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            // compare the resultCode with the SELECT_PICTURE constant
//            if (requestCode == SELECT_PICTURE) {
//                // Get the url of the image from data
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    try {
//                        ImageView imgInput = findViewById(R.id.addImgDisplay);
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
//                        Drawable d = new BitmapDrawable(getResources(), bitmap);
//                        imgInput.setImageDrawable(d);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
}
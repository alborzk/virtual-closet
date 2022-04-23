package com.example.virtualcloset.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.R;

import java.util.ArrayList;
import java.util.List;

// Used to display clothing items in a grid
// Each item in the grid has an image and a title
// Also is able to find clothing with a certain tags and filter what is displayed
public class GridAdapterCloset extends BaseAdapter{

    Context context;
    String[] clothesNames;
    int[] image;
    ClosetManager cm;
    String filter;
    List<Integer> filteredIndexes;
    ArrayList<ClothesItem> closet;
    int numClothes;

    LayoutInflater inflater;

    public GridAdapterCloset(Context context, ClosetManager cm) {
        this.context = context;
        this.clothesNames = cm.getClothesNames();
        this.image = cm.getClothesImgs();
        this.cm = cm;
        this.filter = "All Clothing";
        this.filteredIndexes = new ArrayList();
        this.closet = cm.c.getClothesItems();
        this.numClothes = closet.size();
    }

    @Override
    public int getCount() {
        if(filter.equals("All Clothing")){
            numClothes = closet.size();
        }
        return numClothes;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        // if the closet is being filtered, get the converted indexes
        // e.g. the item in the 2nd space in the filtered grid
        // is the 5th item in the image & clothesNames arrays, so filteredIndexes.get(2) = 5
        int index;
        if(filteredIndexes.size()>=position && !filter.equals("All Clothing")) {
            index = filteredIndexes.get(position);
        } else {
            index = position;
        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        imageView.setImageResource(image[index]);

        TextView textView = convertView.findViewById(R.id.item_name);
        textView.setText(clothesNames[index]);

        return convertView;
    }

    // Gets a String from the dropdown menu of tags and finds the IDs of clothing with that tag
    public void setFilter(String s){
        filter = s;
        filteredIndexes.clear();
        numClothes = 0;
        if(filter.equals("Favourites")){
            for(int i = 0; i < closet.size(); i++){
                ClothesItem curr = closet.get(i);
                if(curr.getFave()){
                    filteredIndexes.add(Integer.valueOf(i));
                    numClothes++;
                }
            }
        } else if (!filter.equals("All Clothing")) {
            for(int i = 0; i < closet.size(); i++){
                List<String> currTags = closet.get(i).getTagNames();
                for(int j = 0; j < currTags.size(); j++){
                    if(currTags.get(j).equals(filter)){
                        filteredIndexes.add(Integer.valueOf(i));
                        numClothes++;
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    //converts from the position of the item in the grid to its ID
    public int getItemIDByPosition(int position){
        int id = position;
        if(!filter.equals("All Clothing")){
            id = filteredIndexes.get(position);
        }
        return id;
    }
}

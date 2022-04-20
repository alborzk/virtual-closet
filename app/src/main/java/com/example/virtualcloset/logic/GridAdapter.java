package com.example.virtualcloset.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.presentation.ClosetActivity;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] clothesNames;
    int[] image;
    ClosetManager cm;
    String filter;
    List<Integer> filteredIndexes;
    ArrayList<ClothesItem> closet;
    int numClothes;

    LayoutInflater inflater;

    public GridAdapter(Context context, ClosetManager cm) {
        this.context = context;
        this.clothesNames = cm.getClothesNames();
        this.image = cm.getClothesImgs();
        this.cm = cm;
        this.filter = "All Clothing";
        this.filteredIndexes = new ArrayList();
        this.closet = cm.c.getClothesItems();
        this.numClothes = closet.size();
    }
    public GridAdapter(Context context, String[] clothesNames, int[] image) {
        this.context = context;
        this.clothesNames = clothesNames;
        this.image = image;
    }

    @Override
    public int getCount() {
        if(filter.equals("All Clothing")){
            numClothes = closet.size();
        }
        return numClothes;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        int index = 0;
        if(filter.equals("All Clothing")){
            index = position;
        } else {
            if(filteredIndexes!=null && filteredIndexes.size()>position) {
                index = filteredIndexes.get(position);
            }
        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        imageView.setImageResource(image[index]);

        TextView textView = convertView.findViewById(R.id.item_name);
        textView.setText(clothesNames[index]);

        return convertView;
    }

    public void setFilter(String s){
        filter = s;
        filteredIndexes.clear();
        numClothes = 0;
        System.out.println("reset");
        for(int i = 0; i < closet.size(); i++){
            List<String> currTags = closet.get(i).getTagNames();
            for(int j = 0; j < currTags.size(); j++){
                if(currTags.get(j).equals(filter)){
                    filteredIndexes.add(Integer.valueOf(i));
                    numClothes++;
                }
            }
        }
        notifyDataSetChanged();
    }
}

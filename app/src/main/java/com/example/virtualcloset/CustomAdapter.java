package com.example.virtualcloset;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<ClothesItem> items;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, ArrayList<ClothesItem> items){
        this.items = items;
        context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return items.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.grid, null);
        TextView name = (TextView) view.findViewById(R.id.gridTitle);
        name.setText(items.get(i).getName());
        return view;
    }
}



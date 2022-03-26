package com.example.virtualcloset.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.virtualcloset.R;

public class GridAdapter2 extends BaseAdapter {

    Context context;
    String[] outfitName;

    LayoutInflater inflater;

    public GridAdapter2(Context context, String[] outfitname) {
        this.context = context;
        this.outfitName=outfitname;
    }

    @Override
    public int getCount() {
        return outfitName.length;
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
            convertView = inflater.inflate(R.layout.grid_item_outfit, null);
        }

        TextView textView = convertView.findViewById(R.id.ouName);

        textView.setText(outfitName[position]);

        return convertView;
    }
}
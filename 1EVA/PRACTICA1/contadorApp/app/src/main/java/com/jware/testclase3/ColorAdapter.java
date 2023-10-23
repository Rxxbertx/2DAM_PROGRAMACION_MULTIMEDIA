package com.jware.testclase3;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class ColorAdapter extends BaseAdapter {
    private final Context context;
    private final String[] colorsArray;

    public ColorAdapter(Context context, String[] colorsArray) {
        this.context = context;
        this.colorsArray = colorsArray;
    }

    @Override
    public int getCount() {
        return colorsArray.length;
    }

    @Override
    public Object getItem(int position) {
        return colorsArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View colorView = new View(context);
        colorView.setBackgroundColor(Color.parseColor(colorsArray[position]));
        colorView.setLayoutParams(new AbsListView.LayoutParams(100, 100)); // Tamaño de la vista de color
        return colorView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        LinearLayout colorLayout = new LinearLayout(context);
        colorLayout.setOrientation(LinearLayout.VERTICAL);
        colorLayout.setBackgroundColor(Color.parseColor(colorsArray[position]));

        View colorView = new View(context);
        //colorView.setBackgroundColor(Color.parseColor(colorsArray[position]));
        colorView.setLayoutParams(new LinearLayout.LayoutParams(150, 100)); // Tamaño de la vista de color
        colorLayout.addView(colorView);

        return colorLayout;
    }
}

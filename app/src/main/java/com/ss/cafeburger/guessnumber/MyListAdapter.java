package com.ss.cafeburger.guessnumber;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cafeburger on 2017/11/8.
 */

public class MyListAdapter extends BaseAdapter {

    private Context context; //context
    private List<Item> items; //data source of the list adapter

    //public constructor
    public MyListAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

// inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.listitem, parent, false);
        }

        // get current item to be displayed
        Item currentItem = (Item) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = convertView.findViewById(R.id.tvNumber);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getNumber());


        // returns the view for the current row
        return convertView;
    }
}

package com.example.shubham.dronemultifunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shubgupta on 2/5/18.
 */

public class InventoryAdapter extends BaseAdapter {

    private ArrayList<InventoryItem> inventoryItems;
    Context context;

    public InventoryAdapter(Context context, ArrayList<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return inventoryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return inventoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InventoryItem item = inventoryItems.get(position);
        if(convertView == null) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.inventory_item_layout, null);
        }
        final TextView name = convertView.findViewById(R.id.name);
        final TextView desc = convertView.findViewById(R.id.desc);

        name.setText(item.getName());
        //desc.setText(item.getDesc());
        return convertView;
    }
}

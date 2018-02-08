package com.example.shubham.dronemultifunction;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by shubgupta on 2/4/18.
 */

public class InventoryFragment extends Fragment implements Client.DataChanged {

    ArrayList<InventoryItem> items;
    InventoryAdapter adapter;
    GridView gridView;
    Client myClient;
    Button connectButton;
    EditText etIp;
    EditText etPort;
    LinearLayout connectionLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_status, container, false);
        gridView = view.findViewById(R.id.gridView);
        connectButton = view.findViewById(R.id.connect);
        etIp = view.findViewById(R.id.ip);
        etPort = view.findViewById(R.id.port);
        connectionLayout = view.findViewById(R.id.connectionLayout);

        items = new ArrayList<>();
        adapter = new InventoryAdapter(getActivity().getApplicationContext(), items);
        gridView.setAdapter(adapter);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etIp.getText().toString().isEmpty() && !etPort.getText().toString().isEmpty()) {
                    myClient = new Client(InventoryFragment.this, etIp.getText().toString(),
                            Integer.valueOf(etPort.getText().toString()),
                            items, adapter);
                    myClient.execute();
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
        return view;
    }

    @Override
    public void onDataChanged() {
        adapter.notifyDataSetChanged();
        gridView.invalidateViews();
        gridView.setAdapter(adapter);
        if((connectionLayout.getVisibility() == View.VISIBLE) && (items.isEmpty() == false)) {
            connectionLayout.setVisibility(View.GONE);
        }
    }
}

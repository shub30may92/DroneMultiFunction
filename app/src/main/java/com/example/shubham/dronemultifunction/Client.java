package com.example.shubham.dronemultifunction;

/**
 * Created by shubgupta on 2/5/18.
 */

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import dbConnection.MongoClientDB;

public class Client extends AsyncTask<Void, Void, String> {

    String dstAddress;
    int dstPort;
    int available;
    String response = "";
    InventoryItem item;
    ArrayList<InventoryItem> list;
    InventoryAdapter adapter;

    public interface DataChanged {
        void onDataChanged();
    }

    public DataChanged delegate = null;

    Client(DataChanged delegate, String addr, int port, ArrayList<InventoryItem> list, InventoryAdapter adapter) {
        dstAddress = addr;
        dstPort = port;
        this.list = list;
        this.adapter = adapter;
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;
//        MongoClientDB client = MongoClientDB.getInstance();

        try {
            socket = new Socket(dstAddress, dstPort);

            while(true) {
                boolean itemAlreadyPresent = false;
                response = "";
                byte[] buffer = new byte[1024];
                if(socket == null) {
                    System.out.println("SOCKET NULL");
                    break;
                }
                if(socket.getInetAddress() == null) {
                    System.out.println("SOCKET INET NULL");
                    break;
                }
                InputStream inputStream = socket.getInputStream();

                available = inputStream.available();
                System.out.print("Characters printed:   ");
                char c = (char) inputStream.read();
                while (c != '\n') {
                    response += c;
                    c = (char) inputStream.read();
                    System.out.print(c);
                }
                System.out.println("RESPONSE:  " + response);
                item = new InventoryItem(response, "some data");
                for(InventoryItem listItem : list) {
                    if(listItem.getName().equals(item.getName())) {
                        itemAlreadyPresent = true;
                    }
                }
                if(!itemAlreadyPresent) {
                    list.add(item);
                    publishProgress();
//                    client.mongoInsertData(item);
                }
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        }
        return response;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        delegate.onDataChanged();
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        System.out.println("CANCELLED with params");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        System.out.println("Cancelled void");
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("RESULT POST EXECUTE:  " +result);
//        list.add(result);
//        adapter.notifyDataSetChanged();
//        super.onPostExecute(result);
    }

}

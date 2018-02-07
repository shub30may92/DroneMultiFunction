package com.example.shubham.dronemultifunction;

/**
 * Created by shubgupta on 2/5/18.
 */

public class InventoryItem {
    String name;
    String desc;

    public InventoryItem(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

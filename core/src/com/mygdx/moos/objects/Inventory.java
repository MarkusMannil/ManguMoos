package com.mygdx.moos.objects;

import java.util.ArrayList;

public class Inventory {
    String id;

    ArrayList<InventoryItem> inventory;

    public Inventory(String id, ArrayList<InventoryItem> inventory) {
        this.id = id;
        this.inventory = inventory;
    }
}

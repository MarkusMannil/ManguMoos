package com.mygdx.moos.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.moos.objects.InventoryEnum;

import java.util.HashMap;

public enum InventoryType {
    ITEM_1(1, "Fish1", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),60,60)[0][0])),
    ITEM_2(2, "Fish2", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),60,60)[0][1])),
    ITEM_3(3, "Fish3", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),60,60)[0][2])),
    ITEM_4(4, "Fish4", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),60,60)[0][3])),
    ITEM_5(5, "Fish5", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),60,60)[0][4]));

    private final int id;
    final String name;
    final Sprite sprite;
    public static final HashMap<Integer, InventoryType> inventoryMap;

    InventoryType(int id, String name, Sprite sprite) {
        this.id = id;
        this.name = name;
        this.sprite = sprite;
    }

    //
    static {
        inventoryMap = new HashMap<>();
        for (InventoryType inventoryType : InventoryType.values()) {
            inventoryMap.put(inventoryType.getId(), inventoryType);
        }
    }


    public static InventoryType getInventoryEnumById(int id){
        return inventoryMap.get(id);
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Sprite getSprite() {
        return sprite;
    }

}


package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public enum InventoryEnum {
    ITEM_1(1, "Fish1", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),64,64)[0][0])),
    ITEM_2(2, "Fish2", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),64,64)[0][1])),
    ITEM_3(3, "Fish3", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),64,64)[0][2])),
    ITEM_4(4, "Fish4", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),64,64)[0][3])),
    ITEM_5(5, "Fish5", new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"),64,64)[0][4]));

    private final int id;
    final String name;
    final Sprite sprite;
    public static final HashMap<Integer, InventoryEnum> inventoryMap;

    InventoryEnum(int id, String name, Sprite sprite) {
        this.id = id;
        this.name = name;
        this.sprite = sprite;
    }

    static {
        inventoryMap = new HashMap<>();
        for (InventoryEnum inventoryEnum : InventoryEnum.values()) {
            inventoryMap.put(inventoryEnum.getId(), inventoryEnum);
        }
        System.out.println(inventoryMap.size());
    }


    public static InventoryEnum getInventoryEnumById(int id){
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

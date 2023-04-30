package com.mygdx.moos.objects.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public enum EntetyEnum {
    KALA1(1,20,100f,300,new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"), 60, 60)[0][0])),
    KALA2(2,50,300f,200,new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"), 60, 60)[0][1])),
    KALA3(3,100,200f,100,new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"), 60, 60)[0][2])),
    KALA4(4,150,50f,100,new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"), 60, 60)[0][3])),
    KALA5(5,500,1000f,600,new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"), 60, 60)[0][4])),;
   private final int id;
   private final int hp;

   private final float speed;



   private final float attackRange;

   private final Sprite sprite;

   private static HashMap<Integer, EntetyEnum> entetyMap;

    static {
        entetyMap = new HashMap<>();
        for (EntetyEnum i:EntetyEnum.values()) {
            entetyMap.put(i.id,i);
        }
    }

    public static EntetyEnum getEnumById(int id){
        return entetyMap.get(id);
    }

    EntetyEnum(int id, int hp, float speed, float attackRange, Sprite sprite) {
        this.id = id;
        this.hp = hp;
        this.speed = speed;
        this.attackRange = attackRange;
        this.sprite = sprite;
    }

    public int getId() {
        return id;
    }

    public int getHp() {
        return hp;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public Sprite getSprite() {
        return sprite;
    }
}

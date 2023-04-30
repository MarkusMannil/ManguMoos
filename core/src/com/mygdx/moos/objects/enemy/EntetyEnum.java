package com.mygdx.moos.objects.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

public enum EntetyEnum {
    KALA1(1,20,1000f,10,new Sprite(new Texture( "bad_debug.png"))),
    KALA2(2,50,300f,10,new Sprite(new Texture( "bad_debug.png"))),
    KALA3(3,100,200f,10,new Sprite(new Texture( "bad_debug.png"))),
    KALA4(4,150,50f,1000,new Sprite(new Texture( "bad_debug.png"))),
    KALA5(5,100,1000f,10,new Sprite(new Texture( "bad_debug.png"))),;
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

package com.mygdx.moos.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum BulletEnum {
    bullet_1(new Sprite(TextureRegion.split(new Texture("bullets2.png"),10,10)[0][0]),200, 150,10),
    bullet_2(new Sprite(TextureRegion.split(new Texture("bullets2.png"),10,10)[0][1]),200, 150,10);

    private float range, speed;
    private int dmg;
    private Sprite sprite;

    BulletEnum(Sprite sprite, float range, float speed, int dmg) {
        this.range = range;
        this.speed = speed;
        this.dmg = dmg;
        this.sprite = sprite;
    }

    public float getRange() {
        return range;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDmg() {
        return dmg;
    }

    public Sprite getSprite() {
        return sprite;
    }
}

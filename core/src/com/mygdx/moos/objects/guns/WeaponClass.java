package com.mygdx.moos.objects.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.moos.Enums.BulletEnum;

public abstract class WeaponClass {
    // graphics
    public Sprite weaponSprite;
    public float wWidth, wHeight;

    public float weaponX,weaponY;

    // get Bullet info
    BulletEnum bulletType;
    // gun attributes
    float fireRate;

    public WeaponClass(float weaponX, float weaponY) {
        this.weaponX = weaponX;
        this.weaponY = weaponY;
    }
    public abstract BulletClass[] shootAtXY(int x, int y, float stateTime);
    public abstract void draw(Batch batch, float xx, float yy, float x, float y);
}

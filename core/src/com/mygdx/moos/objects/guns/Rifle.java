package com.mygdx.moos.objects.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.mygdx.moos.Enums.BulletEnum;

public class Rifle extends WeaponClass {

    private boolean facingR;

    private float last;

    public Rifle(float x, float y) {
        super(x, y);
        this.bulletType = BulletEnum.bullet_1;
        this.weaponSprite = new Sprite(new Texture("guns/tommy.png"));
        this.wHeight = 20;
        this.wWidth = 40;
        //todo if more Bullet types and sprites correct it
        this.bulletType = BulletEnum.bullet_1;
        this.fireRate = 0;
        weaponSprite.setOrigin(30, 10);
        last = 0;
    }

    public BulletClass[] shootAtXY(int x, int y, float stateTime) {
        int range;
        float offset;
        if (last + 3 < stateTime) {

            last = stateTime;
            offset = x > weaponX ? wHeight : 0;
            range = 1000;
            return new BulletClass[]{new BulletClass(x, y, weaponX + offset, weaponY + wHeight / 2, 20, 20, 1000f, range, 10, bulletType.getSprite())
            };
        }
        return new BulletClass[]{};
    }


    public void draw(Batch batch, float xx, float yy, float x, float y) {

        if (xx < x) weaponSprite.setFlip(true, true);
        else if (xx >= x) weaponSprite.setFlip(true, false);

        weaponX = xx + 7;
        weaponY = yy + 20;
        weaponSprite.setPosition(weaponX, weaponY);
        weaponSprite.setRotation((float) (Math.atan2(weaponY - y, weaponX - x) * (180 / Math.PI)));

        weaponSprite.draw(batch);
    }
}

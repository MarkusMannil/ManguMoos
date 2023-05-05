package com.mygdx.moos.objects.guns;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BulletClass {
    // pos -> toDraw
   public float bulletX,bulletY;
    // Bullet collider
    float centerX,centerY;
    float moveX,moveY;
    int width,height;
    float speed; // movement speed
    float range;
    int dmg;
    int viewDirectionRight;
    Sprite sprite;

    public BulletClass(float dirX, float dirY, float bulletX, float bulletY, int width, int height, float speed, float range, int dmg, Sprite sprite) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.centerX = bulletX + (int)(width/2);
        this.centerY = bulletY + (int)(height/2);
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.range = range;
        this.dmg = dmg;
        this.sprite = sprite;
        calcMoveXY(dirX,dirY);
    }
    private void calcMoveXY(float x, float y){
        double alfa = Math.atan(y / x);
        double SINA = Math.sin(alfa);
        double COSA = Math.cos(alfa);
        viewDirectionRight = x >= 0 ? 1 : -1;

        this.moveX = (float) (speed * COSA * viewDirectionRight);
        this.moveY = (float) (speed * SINA * viewDirectionRight);

        sprite.setRotation((float) alfa);
    }
    public boolean move(float delta){
        bulletX += delta * moveX;
        bulletY += delta * moveY;
        return range < Math.sqrt(moveX * moveX + moveY * moveY);
    }
    public void update() {
        sprite.setPosition(bulletX, bulletY);
        sprite.setOrigin((int) (width/2), (int)(height/2));

    }
    public void draw(Batch batch){
        update();
        sprite.draw(batch);
    }
}

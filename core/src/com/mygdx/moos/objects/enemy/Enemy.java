package com.mygdx.moos.objects.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.moos.objects.Player;
import com.mygdx.moos.objects.PlayerProjectile;

public class Enemy {
    // pos
    public float entityX;
    public float entityY;
    public float lastX;
    public float lastY;
    public float centerX;
    public float centerY;

    // graphics
    public Sprite sprite;
    public float height;
    public float width;

    // ms
    public float speed;
    public float distance;
    public float distanceX;
    public float distanceY;
    public float viewDirectionRight = 1;

    // shoot
    public int attackRange;
    public int cooldown = 20;

    public Enemy(float entityX, float entityY, Sprite sprite, float height, float width, float speed, int attackRange, int cooldown) {
        this.entityX = entityX;
        this.entityY = entityY;
        this.lastX = entityX;
        this.lastY = entityY;
        this.sprite = sprite;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.attackRange = attackRange;
        this.cooldown = cooldown;
        this.centerX = entityX + (height / 2);
        this.centerY = entityY + (width / 2);
    }
    public boolean move(float x, float y, float delta) {
        double alfa = Math.atan(y / x);
        double SINA = Math.sin(alfa);
        double COSA = Math.cos(alfa);
        viewDirectionRight = x >= 0 ? 1 : -1;
        boolean isTrue = false;
        this.distanceX = (float) (speed * COSA * viewDirectionRight);
        this.distanceY = (float) (speed * SINA * viewDirectionRight);
        updateLast();
        entityX += distanceX * delta;
        entityY += distanceY * delta;
        centerX += distanceX * delta;
        centerY += distanceY * delta;
        distance += Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return false; //returnType neccesary for projectiles to dissapear at specified distance (attackrange)
    }

    //move at previous angle at specified speed
    public boolean move(float delta) {
        updateLast();
        entityX += distanceX * delta;
        entityY += distanceY * delta;
        centerX += distanceX * delta;
        centerY += distanceY * delta;
        return false;
    }
    public boolean inRangeXY(float x, float y) {
        // returns true if distance between the center position this entity and the given x and y coordinates is lower than attackrange
        double distance = Math.sqrt(Math.pow(x - this.centerX, 2) + Math.pow(y - this.centerY, 2));
        return distance > attackRange;
    }
    public PlayerProjectile shootPlayerProjectileXY( float x, float y){
        if(cooldown < 1){
            // hard code for now
            cooldown = 30;
            return new PlayerProjectile(centerX,centerY, x, y);
        }else {
            cooldown--;
            return null;
        }
    }
    private void updateLast(){
        lastX = entityX;
        lastY = entityY;
    }
    public boolean isColliding(float x, float y) { // true if coordinate in entity hitbox
        return ((this.entityX < x) && (x < this.entityX + width)) && ((this.entityY < y) && (y < this.entityY + height));

    }
}

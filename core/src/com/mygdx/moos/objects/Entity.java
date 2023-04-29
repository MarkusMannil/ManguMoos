package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
    public float entityX;
    public float entityY;
    public Sprite sprite;
    public float speed;

    public int height;
    public int width;
    public float distance;
    public float viewDirectionRight;
    public float distanceX;
    public float distanceY;
    public double COSA;
    public double SINA;
    public double alfa;
    public int attackrange;
    public float centerX;
    public float centerY;
    public Entity projectileType;
    public int cooldown;

    public Entity(String filename, int height, int width, float speed, int cooldown) { // projectileType
        this.sprite = new Sprite(new Texture(filename), height, width);
        this.speed = speed;
        this.attackrange = attackrange;
        this.height = height;
        this.width = width;
        this.cooldown = cooldown;
    }

    public Entity(float entityX, float entityY, String filename, int height, int width, float speed, int attackrange) {
        // non shooting entities
        this.entityX = entityX;
        this.entityY = entityY;
        this.sprite = new Sprite(new Texture(filename), height, width);
        this.speed = speed;
        this.attackrange = attackrange;
        this.centerX = entityX + (float) width / 2;
        this.centerY = entityY + (float) height / 2;
        this.height = height;
        this.width = width;
    }

    public Entity(float entityX, float entityY, String filename, int height, int width, float speed, int attackrange, Entity projectileType) {
        //shooting entities
        this(entityX, entityY, filename, height, width, speed, attackrange);
        this.projectileType = projectileType;
    }


    public boolean isColliding(float x, float y) { // true if coordinate in entity hitbox
        return ((this.entityX < x) && (x < this.entityX + width)) && ((this.entityY < y) && (y < this.entityY + height));

    }

    public boolean move(float x, float y, float delta) { //move this entity toward along the vector v(x,y) at specified speed
        this.alfa = Math.atan(y / x);
        this.SINA = Math.sin(alfa);
        this.COSA = Math.cos(alfa);
        viewDirectionRight = x >= 0 ? 1 : -1;
        boolean isTrue = false;
        this.distanceX = (float) (speed * COSA * viewDirectionRight);
        this.distanceY = (float) (speed * SINA * viewDirectionRight);
        entityX += distanceX * delta;
        entityY += distanceY * delta;
        centerX += distanceX * delta;
        centerY += distanceY * delta;
        distance += Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return false; //returnType neccesary for projectiles to dissapear at specified distance (attackrange)
    }

    public boolean move(float delta) { //move at previous angle at specified speed
        entityX += distanceX * delta;
        entityY += distanceY * delta;
        centerX += distanceX * delta;
        centerY += distanceY * delta;
        return false;
    }

    public boolean inRangeXY(float x, float y) {
        // returns true if distance between the center position this entity and the given x and y coordinates is lower than attackrange
        double distance = Math.sqrt(Math.pow(x - this.centerX, 2) + Math.pow(y - this.centerY, 2));
        return distance > attackrange;
    }

    public boolean inRangeEntity(Entity targetEntity) {// same but target is another entity
        return inRangeXY(targetEntity.centerX, targetEntity.centerY);
    }

    public Projectile[] shootAtXY(float x, float y) { // shoot at XY coordinates on map not camera
        if (cooldown-- < 1) {
            cooldown = this.projectileType.cooldown;
            Projectile[] projectiles = {new Projectile(
                    this, "bullet.png",
                    x - this.centerX,
                    y - this.centerY,
                    this.projectileType.height,
                    this.projectileType.width,
                    this.projectileType.speed,
                    this.attackrange)};
            return projectiles;
        } else {
            cooldown--;
            return null;
        }
    }

    public Projectile[] shootAtEntity(Entity target) { // shoot at given entity
        return shootAtXY(target.centerX, target.centerY);
    }
}

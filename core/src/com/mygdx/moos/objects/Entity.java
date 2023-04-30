package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Entity {
    // enity pos
    public float entityX;
    public float entityY;

    //graphics

    public Sprite sprite;
    public int height;
    public int width;

    // ms
    public float speed;

    // läbitud kaugus  -> vbla vaja lihtsalt speedi
    public float distance;
    public float distanceX;
    public float distanceY;

    // liikumis suunas vaatamine -1 1
    public float viewDirectionRight;


    // laskmis -> need võibolla liigutada meetodisse
    public double COSA;
    public double SINA;
    public double alfa;

    //  kuuli max kaugus
    public int attackrange;

    // playeri keskpunkt
    public float centerX;
    public float centerY;

    public int hp;

    //
    public Entity projectileType;
    public int cooldown = 30;

    public Entity(Sprite sprite, int height, int width, float speed, int cooldown) { // projectileType
        this.sprite = sprite;
        this.speed = speed;
        this.attackrange = attackrange;
        this.height = height;
        this.width = width;
        this.cooldown = cooldown;
    }

    public Entity(float entityX, float entityY, Sprite sprite, int height, int width, float speed, int attackrange, int hp) {
        // non shooting entities
        this.entityX = entityX;
        this.entityY = entityY;
        this.sprite = sprite;
        this.speed = speed;
        this.attackrange = attackrange;
        this.centerX = entityX + (float) width / 2;
        this.centerY = entityY + (float) height / 2;
        this.height = height;
        this.width = width;
        this.hp = hp;
    }

    public Entity(float entityX, float entityY, Sprite sprite, int height, int width, float speed, int attackrange, Entity projectileType, int hp) {
        //shooting entities
        this(entityX, entityY, sprite, height, width, speed, attackrange, hp);
        this.projectileType = projectileType;
    }

    // for bullets
    public boolean isColliding(float x, float y) { // true if coordinate in entity hitbox
        return ((this.entityX < x) && (x < this.entityX + width)) && ((this.entityY < y) && (y < this.entityY + height));

    }

    // move this entity toward along the vector v(x,y) at specified speed

    public boolean move(float x, float y, float delta) {
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

    //move at previous angle at specified speed
    public boolean move(float delta) {
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

    // same but target is another entity
    public boolean inRangeEntity(Entity targetEntity) {
        return inRangeXY(targetEntity.centerX, targetEntity.centerY);
    }


    // shoot at XY coordinates on map not camera
    public Projectile[] shootAtXY(float x, float y) {
        
        if (this.projectileType == null) {
            this.projectileType = new Entity(new Sprite(TextureRegion.split(new Texture("sprites/bulletsBig.png"), 36, 20)[0][2]), 5, 8, 300, 30);
        }
        if (cooldown < 1) {
            cooldown = this.projectileType.cooldown;
            return new Projectile[]{new Projectile(
                    this, new Sprite(TextureRegion.split(new Texture("sprites/bulletsBig.png"), 36, 20)[0][2]),
                    x - this.centerX,
                    y - this.centerY,
                    this.projectileType.height,
                    this.projectileType.width,
                    this.projectileType.speed,
                    this.attackrange)};
        } else {
            cooldown--;
            return null;
        }
    }

    public PlayerProjectile shootPlayerProjectileXY(float x, float y) {
        if (cooldown < 1) {
            // hard code for now
            cooldown = 30;
            return new PlayerProjectile(entityX, entityY, x, y);
        } else {
            cooldown--;
            return null;
        }
    }

    public Projectile[] shootAtEntity(Entity target) { // shoot at given entity
        return shootAtXY(target.centerX, target.centerY);
    }


}

package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Projectile {
    public float projectileX;
    public float projectileY;
    public Sprite sprite;
    public float speed = 50.0f;
    public Player player;

    public float destinationX;

    public float destinationY;

    public double alfa;
    public int direction;
    public float distance;
    public double SINA;
    public double COSA;




    public Projectile(Player player, float destinationX, float destinationY) {
        this.projectileX = player.playerX;
        this.projectileY = player.playerY;
        this.sprite = new Sprite(new Texture("bullet.png"));
        this.player = player;
        this.destinationX = player.playerX + destinationX;
        this.destinationY = player.playerY + destinationY;
        alfa = Math.atan((destinationY / projectileY) / (destinationX / projectileY));
        direction = this.destinationX - projectileX > 0 ? 1 : -1 ;
        SINA = Math.sin(alfa);
        COSA = Math.cos(alfa);
        distance = 0;

    }

    public float moveX(float delta){
        return (float)(speed  * COSA * direction * delta) ;
    }
    public float moveY(float delta){
        return (float)(speed  * SINA * direction * delta);
    }

    public boolean move(float delta){
        boolean isTrue = false;
        float X = moveX(delta);
        float Y = moveY(delta);
        projectileX += X;
        projectileY += Y;
        distance += Math.sqrt(X*X + Y*Y);
        if(distance > 300){

            isTrue = true;
        }
        return isTrue;
    }
}

package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Projectile {
    public float projectileX;
    public float projectileY;
    public Sprite sprite;
    public float speed = 10.0f;
    public Player player;

    public float destinationX;

    public float destinationY;



    public Projectile(Player player, float destinationX, float destinationY) {
        this.projectileX = player.playerX + 19;
        this.projectileY = player.playerY + 59;
        this.sprite = new Sprite(new Texture("bullet.png"));
        this.player = player;
        this.destinationX = player.playerX + destinationX;
        this.destinationY = player.playerY + destinationY;
    }

    public boolean move(float delta){
        boolean isTrue = false;
        if (projectileX - destinationX <= 0) {
            if (Math.abs(projectileX - destinationX)/(speed * delta) > 1) {
                projectileX += (Math.abs(projectileX - destinationX)) * speed * delta;
            } else {
                projectileX = destinationX;
                isTrue = true;
            }
        } else if (projectileX - destinationX > 0) {
            if (Math.abs(projectileX - destinationX)/(speed * delta) > 1) {
                projectileX -= (Math.abs(projectileX - destinationX)) * speed * delta;
            } else {
                projectileX = destinationX;
                isTrue = true;
            }
        }
        if (projectileY - destinationY <= 0) {
            if (Math.abs(projectileY - destinationY)/(speed * delta) > 1) {
                projectileY += (Math.abs(projectileY - destinationY)) * speed * delta;

            } else {
                projectileY = destinationY;
                isTrue = true;
            }
        } else if (projectileY - destinationY > 0) {
            if (Math.abs(projectileY - destinationY)/(speed * delta) > 1) {
                projectileY -= (Math.abs(projectileY - destinationY)) * speed * delta;

            } else {
                projectileY = destinationY;
                isTrue = true;
            }
        }
        return isTrue;
    }
}

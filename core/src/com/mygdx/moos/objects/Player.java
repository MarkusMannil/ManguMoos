package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    public float playerX;
    public float playerY;
    public Sprite sprite;
    public float speed = 120.0f;


    public Player(float playerX, float playerY, Sprite sprite) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.sprite = sprite;
    }
    public void shoot(Projectile projectile, int destinationX, int destinationY){

    }
}

package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BadProjectile{
    public float projectileX;
    public float projectileY;
    public Sprite sprite;
    public float speed = 5.0f;
    public Player player;

    public float destinationX;

    public float destinationY;



    public BadProjectile(Player player, float destinationX, float destinationY) {
        this.projectileX = player.playerX + 19;
        this.projectileY = player.playerY + 59;
        this.sprite = new Sprite(new Texture("Bullet.png"));
        this.player = player;
        this.destinationX = player.playerX + destinationX;
        this.destinationY = player.playerY + destinationY;
    }
}

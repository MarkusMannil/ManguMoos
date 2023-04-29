package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    public TextureRegion[][] texture;
    public Animation<TextureRegion> animatsion;
    public float playerX;
    public float playerY;
    public Sprite sprite;
    public float speed = 120.0f;

    public float size;


    public Player(float playerX, float playerY, Sprite sprite, int size) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.sprite = sprite;
        this.size = size;
        this.texture = TextureRegion.split(sprite.getTexture(), 80, 80);
        if (texture.length > 0)
            this.animatsion = new Animation<TextureRegion>(0.15f, texture[0]);
    }


    public boolean isColliding(float x, float y) {
        if ((x < playerX) || (y < playerY) || (x > playerX + size) || (y > playerY + size)) {
            return false;
        }
        return true;
    }
}

package com.mygdx.moos.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Player {
    public TextureRegion[][] texture;
    public Animation<TextureRegion> animatsion;
    public float playerX;
    public float playerY;

    public float lastX;
    public float lastY;

    public Sprite sprite;
    public float speed = 300f;
    public boolean facing_left = true;
    public float size;

    public int hp;

    public ArrayList<Integer> inventory = new ArrayList<>();


    public Player(float playerX, float playerY, Sprite sprite, int size,int hp) {
        this.playerX = playerX;
        this.playerY = playerY;
        lastX = playerX;
        lastY = playerY;
        this.hp = hp;
        this.sprite = sprite;
        this.size = size;
        this.texture = TextureRegion.split(sprite.getTexture(), 80, 80);
        if (texture.length > 0)
            this.animatsion = new Animation<TextureRegion>(0.15f, texture[0]);


    }


    public boolean isColliding(float x, float y) { // true if coordinate in entity hitbox
        return ((this.playerX < x) && (x < this.playerX + size)) && ((this.playerY < y) && (y < this.playerY + size));
    }







    public void aPressed(float delta, float stateTime) {
        lastX = playerX;
        playerX -= delta * speed;
        if (!facing_left) {
            for (TextureRegion t : animatsion.getKeyFrames()){
                t.flip(true, false);
            }
            facing_left = true;
        }
    }

    public void wPressed(float delta) {
        lastY = playerY;
        playerY += delta * speed;
    }

    public void sPressed(float delta) {
        lastY = playerY;
        playerY -= delta * speed;
    }

    public void dPressed(float delta, float stateTime) {
        lastX = playerX;
        playerX += delta * speed;
        if (facing_left) {
            for (TextureRegion t : animatsion.getKeyFrames()){
                t.flip(true, false);
            }
            facing_left = false;
        }
    }

    public PlayerProjectile leftClickPressed(float delta) {
        // translates coordinates to the center
        int mX = Gdx.input.getX() - (Gdx.graphics.getWidth() / 2); // <kaugus vasakust äärest> -
        int mY = Gdx.graphics.getHeight() / 2 - Gdx.input.getY(); //

        PlayerProjectile playerProjectile = new PlayerProjectile(this, mX, mY);

        return playerProjectile;
    }
}

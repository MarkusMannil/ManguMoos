package com.mygdx.moos.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    public TextureRegion[][] texture;
    public Animation<TextureRegion> animatsion;
    public float playerX;
    public float playerY;
    public Sprite sprite;
    public float speed = 120.0f;
    public boolean facing_left = true;
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

    public void aPressed(float delta, float stateTime) {
        playerX -= delta * speed;
        if (!facing_left) {
            for (TextureRegion t : animatsion.getKeyFrames()){
                t.flip(true, false);
            }
            facing_left = true;
        }
    }

    public void wPressed(float delta) {

        playerY += delta * speed;
    }

    public void sPressed(float delta) {
        playerY -= delta * speed;
    }

    public void dPressed(float delta, float stateTime) {
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
        System.out.println(Gdx.input.getX());
        System.out.println("player " + playerX + " " + playerY);
        System.out.println(mX + " " + mY);
        return playerProjectile;
    }
}

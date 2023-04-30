package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Boat {
    public TextureRegion[][] texture;
    public float boatX;
    public float boatY;

    public float originX, originY;
    public Sprite sprite;
    public float speed = 500.0f;
    public float width = 256;
    public float height = 128;

    public final float startX;
    public final float startY;

    public Vector2 direction;
    public ArrayList<Integer> inventory = new ArrayList<>();

    public Boat(float boatX, float boatY, Sprite sprite) {
        this.boatX = boatX;
        this.boatY = boatY;
        this.sprite = sprite;
        this.direction = new Vector2(1, 0);
        this.originX = 128;
        this.originY = 64;
        this.startX = boatX;
        this.startY = boatY;





    }

    public void update() {
        sprite.setPosition(boatX, boatY);
        sprite.setOrigin(originX, originY);

    }

    public void aPressed(float delta) {
        boatX -= delta * speed * 2;
        direction.x = -1;
        direction.y = 0;
    }

    public void wPressed(float delta) {
        boatY += delta * speed * 2 ;
        direction.y = 1;
        direction.x = 0;
    }

    public void sPressed(float delta) {
        boatY -= delta * speed * 2;
        direction.y = -1;
        direction.x = 0;
    }

    public void dPressed(float delta) {
        boatX += delta * speed * 2;
        direction.x = 1;
        direction.y = 0;

    }
    public void draw(Batch batch) {
        //System.out.println(direction.angleDeg());
        update();

        rotateBoat();

        sprite.draw(batch);
    }

    public void rotateBoat() {

        sprite.setRotation(direction.angleDeg());


    }


    public void waPressed(float delta) {
        direction.y = 1;
        direction.x = -1;
    }

    public void wdPressed(float delta) {
        direction.y = 1;
        direction.x = 1;
    }

    public void saPressed(float delta) {
        direction.y = -1;
        direction.x = -1;
    }

    public void sdPressed(float delta) {
        direction.y = -1;
        direction.x = 1;
    }

    public void shop(){

    }

    public boolean fishing(){
        double chance = Math.random();
        if (chance > 0.8) {
            inventory.add((int)(Math.round(Math.random()*6)));
            return true;
        }
        return false;
    }
}

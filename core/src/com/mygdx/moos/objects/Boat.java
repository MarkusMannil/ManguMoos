package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Boat {
    public TextureRegion[][] texture;
    public float boatX;
    public float boatY;
    public Sprite sprite;
    public float speed = 120.0f;
    public float width = 256;
    public float height = 128;

    public Vector2 direction;


    public Boat(float boatX, float boatY, Sprite sprite) {
        this.boatX = boatX;
        this.boatY = boatY;
        this.sprite = sprite;
        this.direction = new Vector2(1,0);
    }
    public void aPressed(float delta) {
        boatX -= delta * speed;
        direction.x = -1;
    }

    public void wPressed(float delta) {
        boatY += delta * speed;
        direction.y = 1;
    }

    public void sPressed(float delta) {
        boatY -= delta * speed;
        direction.y = -1;
    }

    public void dPressed(float delta) {
        boatX += delta * speed;
        direction.x = -1;

    }
    public void draw(Batch batch){
        //System.out.println(direction.angleDeg());

        batch.draw(sprite,boatX,boatY);
    }
    public  void rotateBoat(){
        sprite.setRotation(direction.angleDeg());

        System.out.println(sprite.getRotation());
    }



}

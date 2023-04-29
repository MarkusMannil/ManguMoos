package com.mygdx.moos.objects;

public class Obsticle {

    float posX;
    float posY;
    float size;

    public Obsticle(float posX, float posY, float size) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
    }

    public boolean isColliding(float x, float y){
        if ((x < posX) || (y < posY) || (x > posX + size) || (y > posY + size)) {
            return false;
        }
        return true;
    }




}

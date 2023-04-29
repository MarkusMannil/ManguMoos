package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Entity{


    public Player(float entityX, float entityY, String filename, int height, int width, float speed, int attackrange, Entity projectileType) {
        super(entityX, entityY, filename, height, width, speed, attackrange, projectileType);
    }
}

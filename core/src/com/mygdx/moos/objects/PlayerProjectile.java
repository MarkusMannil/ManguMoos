package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerProjectile {
    public float projectileX;
    public float projectileY;
    public Sprite sprite;
    public float speed = 500.0f;
    public Player player;

    public Enemy enemy;

    public float destinationX;

    public float destinationY;

    public double alfa;
    public int direction;
    public float distance;
    public double SINA;
    public double COSA;

    public int range = 1000;




    public PlayerProjectile(Player player, float destinationX, float destinationY) {
        this.projectileX = player.playerX;
        this.projectileY = player.playerY;
        this.destinationX = player.playerX + destinationX;
        this.destinationY = player.playerY + destinationY;
        this.sprite = new Sprite(TextureRegion.split(new Texture("sprites/bulletsBig.png"),32,20)[0][0]);
        this.player = player;
        alfa = Math.atan((destinationY / projectileY) / (destinationX / projectileY));
        direction = this.destinationX - projectileX > 0 ? 1 : -1 ;
        SINA = Math.sin(alfa);
        COSA = Math.cos(alfa);
        distance = 0;
    }
    public PlayerProjectile(Enemy player, float destinationX, float destinationY) {
        this.projectileX = player.entityX;
        this.projectileY = player.entityY;
        this.destinationX = player.entityX + destinationX;
        this.destinationY = player.entityY + destinationY;
        this.sprite = new Sprite(TextureRegion.split(new Texture("sprites/bulletsBig.png"),32,20)[0][(int)Math.round(Math.random()*2)]);
        this.enemy = player;
        alfa = Math.atan((destinationY / projectileY) / (destinationX / projectileY));
        direction = this.destinationX - projectileX > 0 ? 1 : -1 ;
        SINA = Math.sin(alfa);
        COSA = Math.cos(alfa);
        distance = 0;
    }


    public PlayerProjectile(float projectileX, float projectileY, float destinationX, float destinationY) {

        this.projectileX = projectileX + 40;
        this.projectileY = projectileY + 40;
        this.destinationX = projectileX + destinationX;
        this.destinationY = projectileY + destinationY;
        this.sprite = new Sprite(TextureRegion.split(new Texture("sprites/bulletsBig.png"),32,20)[0][1]);

        alfa = Math.atan((destinationY / projectileY) / (destinationX / projectileY));
        direction = this.destinationX - projectileX > 0 ? 1 : -1 ;
        SINA = Math.sin(alfa);
        COSA = Math.cos(alfa);
        System.out.println(direction);
        System.out.println(alfa + " " + SINA + " " +COSA);

        distance = 0;
    }

    public float moveX(float delta){
        return (float)(speed  * COSA * direction * delta) ;
    }
    public float moveY(float delta){
        return (float)(speed  * SINA * direction * delta);
    }

    public boolean move(float delta){
        boolean isTrue = false;
        float X = moveX(delta);
        float Y = moveY(delta);
        projectileX += X;
        projectileY += Y;
        distance += Math.sqrt(X*X + Y*Y);
        if(distance > range){

            isTrue = true;
        }
        return isTrue;
    }
    public void draw(Batch batch){

        sprite.setPosition(projectileX,projectileY);
        sprite.setOrigin(80,80);

        sprite.setRotation((float) alfa);

        sprite.draw(batch);
    }
}
package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Projectile  extends Entity{

    public int traveldistance;




    public Projectile(Entity entity,String filename, float distanceX, float distanceY,int height,int width,float speed, int attackRange) {
        super(entity.centerX, entity.centerY, filename, height, width,speed,attackRange);
        this.sprite = new Sprite(new Texture("bullet.png"));
        this.alfa = Math.atan((distanceY) / (distanceX));
        viewDirectionRight = distanceX > 0 ? 1 : -1 ;
        SINA = Math.sin(alfa);
        COSA = Math.cos(alfa);
        this.traveldistance = 0;
        this.distanceX = (float)(speed  * COSA * viewDirectionRight);
        this.distanceY = (float)(speed  * SINA * viewDirectionRight);

    }
    @Override
    public boolean move(float delta){
        super.move(delta);
        traveldistance += this.speed*delta;
        return traveldistance > attackrange;
    }
}
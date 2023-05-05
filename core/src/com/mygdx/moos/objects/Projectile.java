package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Projectile  extends Entity {

    public int traveldistance;

    public Projectile(Entity entity,Sprite sprite, float distanceX, float distanceY,int height,int width,float speed, int attackRange) {
        super(entity.centerX, entity.centerY, sprite, height, width,speed,attackRange,0);
        this.sprite = sprite;
        this.alfa = Math.atan((distanceY) / (distanceX));
        viewDirectionRight = distanceX > 0 ? 1 : -1 ;
        SINA = Math.sin(alfa);
        COSA = Math.cos(alfa);
        this.traveldistance = 0;
        this.distanceX = (float)(speed  * COSA * viewDirectionRight);
        this.distanceY = (float)(speed  * SINA * viewDirectionRight);

    }


    public Projectile(float entityX, float entityY, Sprite sprite, int height, int width, float speed, int attackrange) {
        super(entityX, entityY, sprite, height, width, speed, attackrange,0);
        this.sprite = sprite;
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
    public void draw(Batch batch){
        if(viewDirectionRight == 1){
            sprite.flip(true,false);
        }

        sprite.setPosition(entityX, entityY);
        sprite.setOrigin(height/2,width/2);

        sprite.setRotation((float) alfa);

        sprite.draw(batch);


    }
}

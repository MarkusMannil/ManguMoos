package com.mygdx.moos.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponClass {
    public Sprite bulletSprite;
    public int height;
    public int width;
    public float projectileSpeed;
    public int attackrange;

    public WeaponClass(String bulletSpriteFilename, int height, int width, float projectileSpeed, int attackrange) {
        this.bulletSprite = new Sprite(new Texture(bulletSpriteFilename));
        this.height = height;
        this.width = width;
        this.projectileSpeed = projectileSpeed;
        this.attackrange = attackrange;
    }
  public Projectile[] shootAtXY(){
        return null;
  }
}

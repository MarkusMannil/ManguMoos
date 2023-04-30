package com.mygdx.moos.objects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Shotgun extends WeaponClass {
    public Shotgun(String bulletSpriteFilename, int height, int width, float projectileSpeed, int attackrange) {
        super(bulletSpriteFilename, height, width, projectileSpeed, attackrange);
    }
    public Projectile[] shootAtXY(float shooterX,float shooterY,float targetX,float targetY){
        int viewDirectionRight = targetX - shooterX > 0 ? 1:-1;
        Projectile[] projectiles = new Projectile[9];

        for (int i = 0; i < 9; i++) {
            double alfa = Math.atan(targetY - shooterY / targetX - shooterY) + 0.001;
            double SINA = Math.sin(alfa);
            double COSA = Math.cos(alfa);
            float X = shooterX + (float)(projectileSpeed  * COSA * viewDirectionRight);
            float Y = shooterY + (float)(projectileSpeed  * SINA * viewDirectionRight);

            projectiles[i] = new Projectile(
                    shooterX,
                    shooterY,
                    bulletSprite,
                    this.height,
                    this.width,
                    this.projectileSpeed,
                    this.attackrange
            );
        }
        return projectiles;
    }
}

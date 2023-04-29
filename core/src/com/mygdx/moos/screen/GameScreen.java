package com.mygdx.moos.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.objects.Entity;
import com.mygdx.moos.objects.Player;
import com.mygdx.moos.objects.Projectile;

import java.util.ArrayList;

public class GameScreen extends InputAdapter implements Screen {

    OrthographicCamera camera;
    Player player;

    Entity bad;
    Entity good;
    Batch batch;
    ArrayList<Projectile> playerProjectiles = new ArrayList<>();
    ArrayList<Projectile> enemyProjectiles = new ArrayList<>();

    ArrayList<Entity> enteties = new ArrayList<>();
    boolean facing_left = true;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        player = new Player(0, 0, "rott.png",150 ,150,500,600, new Entity("bullet.png",3,3,300,10));
        bad = new Entity(700, 0, "bad_debug.png", 65,65,100,400, new Entity("bullet.png",3,3,300,200) );
        good = new Entity(-700, 0, "bad_debug.png", 65,65,1,100);

        enteties.add(bad);
        enteties.add(good);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);
        batch.setProjectionMatrix(camera.combined);


        batch.begin();

        camera.position.set(player.entityX, player.entityY, 0);
        camera.update();
        int x = 0;
        int y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 1;
            if (!facing_left) {
                player.sprite.flip(true, false);
                facing_left = !facing_left;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= 1;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 1;
            if (facing_left) {
                player.sprite.flip(true, false);
                facing_left = !facing_left;
            }
        }
        if(x != 0 || y != 0){
            player.move(x,y,delta);
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int mX = (int)(player.entityX + Gdx.input.getX() - (Gdx.graphics.getWidth() / 2));//+ (int)(Math.random()*40));
            int mY = (int)(player.entityY + Gdx.graphics.getHeight() - Gdx.input.getY() - (Gdx.graphics.getHeight() / 2));// + (int)(Math.random()*40);
            Projectile[] newPlayerProjectile = player.shootAtXY(mX,mY);
            if(newPlayerProjectile != null){
                for (Projectile projectile : newPlayerProjectile) {
                    playerProjectiles.add(projectile);

                }
            }


        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            dispose();
        }

        for (int i = 0; i < playerProjectiles.size(); i++) {
            boolean arrived = playerProjectiles.get(i).move(delta);
            batch.draw(playerProjectiles.get(i).sprite, playerProjectiles.get(i).entityX, playerProjectiles.get(i).entityY);

            for (Entity p1 : enteties) {

                if(p1.isColliding(playerProjectiles.get(i).centerX, playerProjectiles.get(i).centerY)){
                    System.out.println("HIT");
                    arrived = true;
                    break;

                }
            }

            if (arrived) {
                playerProjectiles.remove(playerProjectiles.get(i));
                i--;
            }
        }

        for (int i = 0; i < enemyProjectiles.size(); i++) {
            boolean arrived = enemyProjectiles.get(i).move(delta);
            batch.draw(enemyProjectiles.get(i).sprite, enemyProjectiles.get(i).entityX, enemyProjectiles.get(i).entityY);


                if(player.isColliding(enemyProjectiles.get(i).centerX, enemyProjectiles.get(i).centerY)){
                    System.out.println("playerHIT");
                    arrived = true;
                }

            if (arrived) {
                enemyProjectiles.remove(enemyProjectiles.get(i));
                i--;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            playerProjectiles = new ArrayList<>();
        }
        if(bad.inRangeEntity(player)){
            bad.move(player.entityX - bad.entityX,player.entityY- bad.entityY,delta);
        }else{
            Projectile[] newEnemyProjectiles = bad.shootAtEntity(player);
            if(newEnemyProjectiles != null){
                for (Projectile enemyProjectile : newEnemyProjectiles) {
                    enemyProjectiles.add(enemyProjectile);

                }
            }
        }
        batch.draw(good.sprite, good.entityX, good.entityY);
        batch.draw(bad.sprite, bad.entityX, bad.entityY);
        batch.draw(player.sprite, player.entityX, player.entityY);
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        batch.dispose();
    }
}

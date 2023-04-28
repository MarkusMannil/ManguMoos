package com.mygdx.moos.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.objects.Player;
import com.mygdx.moos.objects.Projectile;

import java.util.ArrayList;

public class GameScreen extends InputAdapter implements Screen {

    OrthographicCamera camera;
    Player player;

    Player bad;
    Batch batch;
    ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    ArrayList<Player> enteties = new ArrayList<Player>();
    boolean facing_left = true;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        player = new Player(0, 0, new Sprite(new Texture("rott.png")), 80);
        bad = new Player(700, 0, new Sprite(new Texture("bad_debug.png")), 65);

        enteties.add(bad);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);
        batch.setProjectionMatrix(camera.combined);


        batch.begin();

        camera.position.set(player.playerX, player.playerY, 0);
        camera.update();


        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.playerX -= delta * player.speed;
            if (!facing_left) {
                player.sprite.flip(true, false);
                facing_left = !facing_left;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.playerY += delta * player.speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.playerY -= delta * player.speed;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.playerX += delta * player.speed;
            if (facing_left) {
                player.sprite.flip(true, false);
                facing_left = !facing_left;
            }
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int mX = Gdx.input.getX() - (Gdx.graphics.getWidth() / 2);
            int mY = Gdx.graphics.getHeight() - Gdx.input.getY() - (Gdx.graphics.getHeight() / 2);
            Projectile projectile = new Projectile(player, mX, mY);
            projectiles.add(projectile);
            System.out.println(Gdx.input.getX());
            System.out.println("player " + player.playerX + " " + player.playerY);
            System.out.println(mX + " " + mY);


        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            dispose();
        }
        for (Projectile p : projectiles) {
            boolean arrived = p.move(delta);
            batch.draw(p.sprite, p.projectileX, p.projectileY);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            boolean arrived = projectiles.get(i).move(delta);
            batch.draw(projectiles.get(i).sprite, projectiles.get(i).projectileX, projectiles.get(i).projectileY);

            for (Player p1 : enteties) {
                if(p1.isColliding(projectiles.get(i).projectileX, projectiles.get(i).projectileY)){
                    System.out.println("HIT");
                    arrived = true;
                    break;

                }

            }

            if (arrived) {
                projectiles.remove(projectiles.get(i));
                i--;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            projectiles = new ArrayList<>();
        }


        batch.draw(bad.sprite, bad.playerX, bad.playerY);
        batch.draw(player.sprite, player.playerX, player.playerY);

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

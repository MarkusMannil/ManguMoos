package com.mygdx.moos.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.GeimClass;
import com.mygdx.moos.MegaTile;
import com.mygdx.moos.objects.Player;
import com.mygdx.moos.objects.PlayerProjectile;

import java.util.ArrayList;

public class GameScreen extends InputAdapter implements Screen {

    OrthographicCamera camera;
    Player player;

    Player bad;
    Batch batch;
    MapLayers layers;
    ArrayList<PlayerProjectile> playerProjectiles = new ArrayList<PlayerProjectile>();

    ArrayList<Player> enteties = new ArrayList<Player>();
    boolean facing_left = true;

    float stateTime;

    TiledMap map = new TiledMap();


    MegaTile tile;

    OrthogonalTiledMapRenderer renderer;

    Texture border;

    float mapLoading;
    boolean pause;



    public GameScreen(GeimClass geimClass)  {
        this.geimClass = geimClass;
        player = new Player(5000, 5000, new Sprite(new Texture("sprites/paadiAnts.png")), 80);
        bad = new Player(5100, 5000, new Sprite(new Texture("bad_debug.png")), 65);
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        renderer = new OrthogonalTiledMapRenderer(map);
        layers = map.getLayers();
        generateMap(10);
        enteties.add(bad);
        pause = false;
        border = new Texture("assets/border.png");
    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);
        batch = renderer.getBatch();
        //System.out.println(player.playerX +" "+ player.playerY);

        batch.setProjectionMatrix(camera.combined);
        renderer.setView(camera);
        camera.update();
        renderer.render();
        batch.begin();

        camera.position.set(player.playerX, player.playerY, 0);


        if (pause) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                pause = false;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            generalUpdate(delta,stateTime);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            playerProjectiles = new ArrayList<>();
        }


        batch.draw(bad.sprite, bad.playerX, bad.playerY);
        batch.draw((player.animatsion.getKeyFrame(stateTime, true)), player.playerX, player.playerY, 80, 80);

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

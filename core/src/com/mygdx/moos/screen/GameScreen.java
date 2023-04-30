package com.mygdx.moos.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.GeimClass;
import com.mygdx.moos.tiles.MegaTile;
import com.mygdx.moos.objects.Player;
import com.mygdx.moos.objects.PlayerProjectile;

import java.util.ArrayList;

public class GameScreen extends InputAdapter implements Screen {

    GeimClass geimClass;
    OrthographicCamera camera;
    OrthographicCamera UIcamera;
    Player player;

    Player bad;
    Batch batch;
    Batch borderBatch;
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
        player = new Player(5000, 5000, new Sprite(new Texture("sprites/paadiAnts.png")), 80,100);
        bad = new Player(5100, 5000, new Sprite(new Texture("bad_debug.png")), 65,100);
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        UIcamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.setToOrtho(false);
        UIcamera.setToOrtho(false);
        renderer = new OrthogonalTiledMapRenderer(map);
        layers = map.getLayers();
        generateMap(10);
        enteties.add(bad);
        pause = false;
        border = new Texture("assets/border.png");
        borderBatch = new SpriteBatch();
    }
    public void  generateMap(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                layers.add(new MegaTile(i, j).generateMapLayer());
            }
        }

    }

    public void generalUpdate(float delta,float stateTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.aPressed(delta,stateTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.wPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.sPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.dPressed(delta,stateTime);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)){
            camera.zoom += 2;
            System.out.println("zoom +?");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)){
            camera.zoom -= 2;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

            playerProjectiles.add(player.leftClickPressed(delta));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause = true;
            pause();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            
        }
        for (int i = 0; i < playerProjectiles.size(); i++) {
            boolean arrived = playerProjectiles.get(i).move(delta);

            for (Player p1 : enteties) {
                if (p1.isColliding(playerProjectiles.get(i).projectileX, playerProjectiles.get(i).projectileY)) {
                    System.out.println("HIT");
                    arrived = true;
                    break;
                }
            }
            if (arrived) {
                playerProjectiles.remove(playerProjectiles.get(i));
                i--;
            } else
                batch.draw(playerProjectiles.get(i).sprite, playerProjectiles.get(i).projectileX, playerProjectiles.get(i).projectileY);
        }
    }

    @Override
    public void render(float delta) {
        stateTime += delta;




        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);
        batch = renderer.getBatch();
        //System.out.println(player.playerX +" "+ player.playerY);
        UIcamera.update();
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

        borderBatch.setProjectionMatrix(UIcamera.combined);
        borderBatch.begin();
        borderBatch.draw(border,0,0,1920,1080);
        borderBatch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        geimClass.setScreen(new PauseScreen(geimClass, geimClass.gameScreen));
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

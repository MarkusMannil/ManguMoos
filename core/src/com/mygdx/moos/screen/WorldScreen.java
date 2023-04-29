package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.GeimClass;
import com.mygdx.moos.MegaTile;
import com.mygdx.moos.objects.Boat;
import com.mygdx.moos.objects.Obsticle;
import com.mygdx.moos.objects.Player;
import com.mygdx.moos.objects.PlayerProjectile;

import java.util.ArrayList;

public class WorldScreen implements Screen {

    OrthographicCamera camera;
    Boat boat;
    ArrayList<Obsticle> obsticles = new ArrayList<Obsticle>();
    Batch batch;
    TiledMap map;

    OrthogonalTiledMapRenderer renderer;
    MegaTile tile;
    MapLayers layers;

    GeimClass geimClass;

    float stateTime;

    public WorldScreen(GeimClass geimClass) {
        this.geimClass = geimClass;
        boat = new Boat(0, 0, new Sprite(new Texture("sprites/paat.png")));
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        map = new TiledMap();
        renderer = new OrthogonalTiledMapRenderer(map);
        tile = new MegaTile(0, 0);
        layers = map.getLayers();
        generateMap(9);

    }
    public void  generateMap(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                layers.add(new MegaTile(i, j).generateMapLayer());
            }
        }
    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);
        batch = renderer.getBatch();


        batch.setProjectionMatrix(camera.combined);
        renderer.setView(camera);
        camera.update();
        renderer.render();
        batch.begin();
        camera.position.set(boat.boatX, boat.boatY, 0);
        boat.draw(batch);

        generalUpdate(delta,stateTime);

        batch.end();

        boat.rotateBoat();
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

    }
    public void generalUpdate(float delta,float stateTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            boat.aPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            boat.wPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            boat.sPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            boat.dPressed(delta);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)){
            camera.zoom += 2;
            System.out.println("zoom +?");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)){
            camera.zoom -= 2;
        }

    }
}

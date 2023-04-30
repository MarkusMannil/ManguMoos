package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.GeimClass;
import com.mygdx.moos.tiles.MegaTile;
import com.mygdx.moos.objects.Boat;
import com.mygdx.moos.objects.Obsticle;

import java.util.ArrayList;

public class WorldScreen implements Screen {

    OrthographicCamera camera;
    OrthographicCamera UIcamera;
    Boat boat;
    ArrayList<Obsticle> obsticles = new ArrayList<Obsticle>();
    Batch batch;
    Batch borderBatch;
    TiledMap map;

    OrthogonalTiledMapRenderer renderer;
    MegaTile tile;
    MapLayers layers;
    GeimClass geimClass;
    Texture border;
    Texture inv;
    Texture invShow;
    boolean pause;
    boolean invent = false;

    float stateTime;

    public WorldScreen(GeimClass geimClass) {
        this.geimClass = geimClass;
        boat = new Boat(16 * 64 * 30 - 590, 16 * 64 *30, new Sprite(new Texture("sprites/paat.png"), 256, 128));
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        UIcamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        UIcamera.setToOrtho(false);
        map = new TiledMap();
        renderer = new OrthogonalTiledMapRenderer(map);
        tile = new MegaTile(0, 0);
        layers = map.getLayers();
        pause = false;

        border = new Texture("assets/border.png");
        inv = new Texture("assets/buttons/inventory.png");
        invShow = new Texture("assets/buttons/invShow.png");
        borderBatch = new SpriteBatch();
        generateMap(25);
        //layers.add(new MegaTile(0,0).generateBoatMapLayer());

    }

    public void generateMap(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 15 && j == 15) {
                    layers.add(new MegaTile(i, j).generateHouseMapLayer());
                } else
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
        camera.position.set(boat.boatX + boat.originX, boat.boatY + boat.originY, 0);
        boat.draw(batch);

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
            generalUpdate(delta, stateTime);
        }

        batch.end();

        //x=134
        //y=262
        borderBatch.setProjectionMatrix(UIcamera.combined);
        borderBatch.begin();


        borderBatch.draw(border, 0, 0, 1920, 1080);
        if (!invent) borderBatch.draw(inv, 1820, 980, 64, 64);
        else if (invent) {
            borderBatch.draw(invShow, 1630, 534, 268, 524);
            display_inv();
        }
        borderBatch.end();


    }

    public void display_inv() {
        int inventory_size = boat.inventory.size();

        int j = 0;

        for (int i = 0; i < inventory_size; i++) {
            if (i % 3 == 0) {
                j += 1;
            }

            int fishType = boat.inventory.get(i);


            Sprite fish = new Sprite(TextureRegion.split(new Texture("assets/sprites/fishSpriteTest.png"), 60, 60)[0][fishType - 1]);

            borderBatch.draw(fish, 1642 + (i % 3) * 88, 985 - j * 88, 58, 58);
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        geimClass.setScreen(new PauseScreen(geimClass, geimClass.worldScreen));
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

    public void generalUpdate(float delta, float stateTime) {
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
        if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.W)) {
            boat.waPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            boat.wdPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            boat.saPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            boat.sdPressed(delta);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            // DO SHIT
            if ( 16 * 64 * 30 - 290 > boat.boatX && boat.boatX < 16 * 64 * 30 - 890 && 16 * 64 * 30 - 300 < boat.boatY && 16 * 64 * 30 + 300 > boat.boatY){
                System.out.println("shop");
            }
            else if (boat.fishing()){
                double radius = Math.sqrt(Math.pow(Gdx.input.getX() - boat.startX, 2) + Math.pow(Gdx.input.getY() - boat.startY, 2));
                geimClass.setScreen(new BoatFightScreen(geimClass,radius));
            }
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            System.out.println("I");
            invent = !invent;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            camera.zoom += 2;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            camera.zoom -= 2;
        }
    }
}

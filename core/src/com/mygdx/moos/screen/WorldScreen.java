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
import com.badlogic.gdx.math.Vector2;
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

    ArrayList<Vector2> colliders;


    Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/music/Main_theme.mp3"));

    public WorldScreen(GeimClass geimClass) {
        this.geimClass = geimClass;
        boat = new Boat(16 * 64 * 30 - 590, 16 * 64 * 30, new Sprite(new Texture("sprites/paat.png"), 256, 128));
        map = new TiledMap();
        layers = map.getLayers();
        generateMap(25);
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        UIcamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        UIcamera.setToOrtho(false);
        renderer = new OrthogonalTiledMapRenderer(map);
        pause = false;

        border = new Texture("assets/border.png");
        inv = new Texture("assets/buttons/inventory.png");
        invShow = new Texture("assets/buttons/invShow.png");
        borderBatch = new SpriteBatch();

        //fishiGoal = (int) Math.round(Math.random()*12+3);
        //layers.add(new MegaTile(0,0).generateBoatMapLayer());
        addColliders();

        long id = sound.play(1.0f);
        sound.setLooping(id, true);

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

        //checkCollision();

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

    public void addColliders() {
        int[] map = new MegaTile().getHouse();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (map[30 * j + i] == 18) {
                    colliders.add(new Vector2(i + 64 * 30 * 15, j + 64 * 30 * 15));
                }
            }
        }
    }

    public void checkCollision() {

        for (Vector2 collider : colliders) {
            if (collides(boat.boatX, boat.boatY, 256, 128, collider.x, collider.y, 64, 64)) {
                System.out.println("COLLISION");
                boat.boatX = boat.lastX;
                boat.boatY = boat.lastY;

            }
        }
    }


    private boolean collides(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        return ((x1 < x2) && (x2 < x1 + w1)) && ((y1 < y2) && (y2 < y1 + h1)) ||
                ((x1 < x2 + w2) && (x2 + w2 < x1 + w1)) && ((y1 < y2) && (y2 < y1 + h1)) ||
                ((x1 < x2) && (x2 < x1 + w1)) && ((y1 < y2 + h2) && (y2 + h2 < y1 + h1)) ||
                ((x1 < x2 + w2) && (x2 + w2 < x1 + w1)) && ((y1 < y2 + h2) && (y2 + h2 < y1 + h1));


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
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            // DO SHIT
            if ( 16 * 64 * 30 - 290 > boat.boatX && boat.boatX < 16 * 64 * 30 - 890 && 16 * 64 * 30 - 300 < boat.boatY && 16 * 64 * 30 + 300 > boat.boatY){
                System.out.println("shop");
            }
            else if (boat.fishing()){
                double radius = Math.sqrt(Math.pow(Gdx.input.getX() - boat.startX, 2) + Math.pow(Gdx.input.getY() - boat.startY, 2));
                sound.stop();
                geimClass.setScreen(new BoatFightScreen(geimClass, radius));
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.M) && camera.zoom <10) {
            camera.zoom += 2;
            System.out.println(camera.zoom);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N) && camera.zoom > 3 ){
            camera.zoom -= 2;
        }
    }
}

package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.GeimClass;
import com.mygdx.moos.objects.*;
import com.mygdx.moos.Enums.EntetyEnum;
import com.mygdx.moos.tiles.MegaTile;

import java.util.ArrayList;
import java.util.Arrays;

public class BoatFightScreen extends InputAdapter implements Screen {

    // game class to switch between screens
    GeimClass geimClass;
    Player player; // -> Entity
    Batch batch;
    Batch uiBatch;
    BitmapFont font;
    ArrayList<Entity> enteties;

    ArrayList<PlayerProjectile> playerProjectiles = new ArrayList<>();

    ArrayList<Projectile> enemyProjectiles = new ArrayList<>();
    TiledMap map;

    MapLayers layers;

    OrthogonalTiledMapRenderer renderer;

    OrthographicCamera camera;

    OrthographicCamera uiCamera;

    Texture border;

    float stateTime;

    double radius;

    ArrayList<Vector2> colliders = new ArrayList<>();


    public BoatFightScreen(GeimClass geimClass) {
        this.geimClass = geimClass;
        addColliders();
    }

    @Override
    public void show() {
        // projectiles and enemies
        playerProjectiles = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
        player = new Player(48 * 64, 30 * 64, new Sprite(new Texture("sprites/paadiAnts.png")), 80, 100);
        enteties = new ArrayList<>();
        radius = geimClass.worldScreen.radius;
        addEnemies();
        //
        font = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCamera.setToOrtho(false);

        map = new TiledMap();

        renderer = new OrthogonalTiledMapRenderer(map);
        layers = map.getLayers();

        layers.add(new MegaTile(0, 0).generateBoatMapLayer());



        border = new Texture("border.png");

        uiBatch = new SpriteBatch();

        System.out.println(enteties.size());

    }

    @Override
    public void render(float delta) {
        stateTime += delta;

        checkCollision();

        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);
        uiCamera.update();
        renderer.setView(camera);
        batch = renderer.getBatch();
        camera.update();


        renderer.render();

        batch.begin();

        upDateEntity(batch, delta);
        camera.position.set(player.playerX, player.playerY, 0);
        batch.draw((player.animatsion.getKeyFrame(stateTime, true)), player.playerX, player.playerY, 80, 80);
        generalUpdate(delta, stateTime);

        batch.end();

        font.getData().setScale(3, 3);
        uiBatch.begin();
        uiBatch.draw(border, 0, 0, 1920, 1080);
        font.setColor(255, 255, 255, 1);
        font.draw(uiBatch, "SPAM MOUSE1 TO SHOOT", 1920 / 2 - 200, 200);
        uiBatch.end();
    }

    public void upDateEntity(Batch batch, float delta) {
        if(enteties.size() == 0){

            geimClass.setScreen(geimClass.worldScreen);
        }

        for (int i = 0; i < enteties.size(); i++) {
            Entity e = enteties.get(i);

            if (e.hp < 0) {

                System.out.println("VASTANE SURI");
                enteties.remove(e);
                i --;

            } else {
                batch.draw(e.sprite, e.entityX, e.entityY);
                if (e.inRangeXY(player.playerX, player.playerY)) {
                    e.move(player.playerX - e.entityX, player.playerY - e.entityY, delta);
                } else {
                    Projectile[] newEnemyProjectiles = e.shootAtXY(player.playerX, player.playerY);
                    if (newEnemyProjectiles != null) {
                        enemyProjectiles.addAll(Arrays.asList(newEnemyProjectiles));
                    }
                }

            }
        }
    }


    public void generalUpdate(float delta, float stateTime) {
        if(player.hp < 0){
            System.out.println("DEAD YOU");

            geimClass.setScreen(geimClass.titlescreen);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.aPressed(delta, stateTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.wPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.sPressed(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.dPressed(delta, stateTime);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M) && camera.zoom < 2) {
            camera.zoom += 2;

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.N) && camera.zoom > 1 ) {
            camera.zoom -= 2;
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            playerProjectiles.add(player.leftClickPressed(delta));

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //pause = true;
            pause();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (int i = 0; i < playerProjectiles.size(); i++) {
            boolean arrived = playerProjectiles.get(i).move(delta);

            for (Entity p1 : enteties) {
                if (p1.isColliding(playerProjectiles.get(i).projectileX, playerProjectiles.get(i).projectileY)) {
                    p1.hp -= 100;
                    System.out.println("HIT");
                    arrived = true;
                    break;
                }
            }
            if (arrived) {
                playerProjectiles.remove(playerProjectiles.get(i));
                i--;
            } else
                playerProjectiles.get(i).draw(batch);
        }

        for (int i = 0; i < enemyProjectiles.size(); i++) {

            boolean arrived = enemyProjectiles.get(i).move(delta);
            if (player.isColliding(enemyProjectiles.get(i).centerX, enemyProjectiles.get(i).centerY)) {
                player.hp -= 10;
                System.out.println("PLAYER HIT");
                arrived = true;
            }

            if (arrived) {

                enemyProjectiles.remove(enemyProjectiles.get(i));
                i--;
            } else
                batch.draw(enemyProjectiles.get(i).sprite, enemyProjectiles.get(i).entityX, enemyProjectiles.get(i).entityY);
        }
    }


    public void addColliders() {
        int[] map = new MegaTile().getLong();
        for (int i = 20; i < 60; i++) {
            for (int j = 20; j < 40; j++) {
                if (map[80 * j + i] == 7 || map[80 * j + i] == 8) {
                    colliders.add(new Vector2(i * 64, j * 64));
                }
            }
        }
    }

    public void checkCollision() {
        if (!(player.playerX > 18 && player.playerX < 37 && player.playerY > 18 && player.playerY < 37)) {
            for (Vector2 collider : colliders) {
                if (collides(player.playerX, player.playerY, player.size, player.size, collider.x, collider.y, 64, 64)) {
                    System.out.println("COLLISION");
                    player.playerX = player.lastX;
                    player.playerY = player.lastY;
                }

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
    public void addEnemies(){
        double computed = Math.log10(radius + 1);
        System.out.println(computed);
        int howManyEnemies = (int) (computed * Math.random() + 1);

        for (int i = 0; i < howManyEnemies; i++) {
            int typeOfEnemy = (int) ((computed) * Math.random() + 1);
            geimClass.worldScreen.boat.addFish(typeOfEnemy);
            switch (typeOfEnemy) {
                case 1:
                    //
                    enteties.add(new Entity(40 * 64, 30 * 64 + (64*2*i),EntetyEnum.getEnumById(1).getSprite(), 60, 60, EntetyEnum.getEnumById(1).getSpeed(), (int) EntetyEnum.getEnumById(1).getAttackRange(), EntetyEnum.getEnumById(1).getHp()));
                    break;
                case 2:
                    //
                    enteties.add(new Entity(40 * 64, 30 * 64 + (64*2*i),EntetyEnum.getEnumById(2).getSprite(), 60, 60, EntetyEnum.getEnumById(2).getSpeed(), (int) EntetyEnum.getEnumById(2).getAttackRange(), EntetyEnum.getEnumById(2).getHp()));
                    break;
                case 3:
                    //
                    enteties.add(new Entity(40 * 64, 30 * 64 + (64*2*i),EntetyEnum.getEnumById(3).getSprite(), 60, 60, EntetyEnum.getEnumById(3).getSpeed(), (int) EntetyEnum.getEnumById(3).getAttackRange(), EntetyEnum.getEnumById(3).getHp()));
                    break;
                case 4:
                    //
                    enteties.add(new Entity(40 * 64, 30 * 64 + (64*2*i),EntetyEnum.getEnumById(4).getSprite(), 60, 60, EntetyEnum.getEnumById(4).getSpeed(), (int) EntetyEnum.getEnumById(4).getAttackRange(), EntetyEnum.getEnumById(4).getHp()));
                    break;
                case 5:
                    //
                    enteties.add(new Entity(40 * 64, 30 * 64 + (64*2*i),EntetyEnum.getEnumById(5).getSprite(), 60, 60, EntetyEnum.getEnumById(5).getSpeed(), (int) EntetyEnum.getEnumById(5).getAttackRange(), EntetyEnum.getEnumById(5).getHp()));
                    break;
                default:

            }
            //enteties.add(new Entity(48 * 64, 30 * 64, new Sprite(new Texture( "bad_0.png")), 60, 60, 300f, 1000, 100));

        }
    }
}

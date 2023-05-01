package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.moos.GeimClass;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.moos.objects.Boat;

public class MerchantScreen implements Screen {
    //this for accepting delivery missions and selling merchandise
    Texture shop;
    Texture button;
    GeimClass geimClass;
    Batch batch;
    BitmapFont font;
    Boat boat;
    int fishgoal;
    Boolean fishBool = false;




    public MerchantScreen(GeimClass geimClass, int fishgoal, Boat boat,boolean thkayou) {
        this.geimClass = geimClass;
        this.fishgoal = fishgoal;
        this.boat = boat;
        fishBool = true;
    }

    @Override
    public void show() {

        shop = new Texture("assets/shop.png");
        batch = new SpriteBatch();
        font = new BitmapFont();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_ALPHA_BITS);


        String text = "Giv " + fishgoal + " fish...";

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            resume();
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            if (fishgoal <= boat.inventory.size()) {
                for (int i = fishgoal - 1; i < 0; i--) {
                    boat.inventory.remove(boat.inventory.get(i));
                }
                fishBool = true;
                if (fishBool) fishgoal = (int) Math.round(Math.random() * 12 + 3);
                text = "danke for fish";
            } else {
                fishBool = false;
                text = "get more fish, u need " + fishgoal;
            }

        }
        batch.begin();


        font.getData().setScale(3, 3);
        batch.draw(shop, 2, 2, 1920, 1080);
        font.setColor(0, 0, 0, 1);
        font.draw(batch, text, 1920 / 2 - 80, 200);

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
        geimClass.setScreen(geimClass.worldScreen);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

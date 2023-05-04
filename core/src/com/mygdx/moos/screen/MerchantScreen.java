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

    int state;
    String text = "";


    public MerchantScreen(GeimClass geimClass, int fishgoal, Boat boat, boolean thkayou) {
        this.geimClass = geimClass;
        this.fishgoal = fishgoal;
        this.boat = geimClass.worldScreen.boat;
        fishBool = false;
    }

    public MerchantScreen(GeimClass geimClass) {
        this.geimClass = geimClass;
        this.fishgoal = geimClass.worldScreen.fishiGoal;
        this.boat = geimClass.worldScreen.boat;
    }


    @Override
    public void show() {

        shop = new Texture("shop.png");
        batch = new SpriteBatch();
        font = new BitmapFont();
        state = 0;


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_ALPHA_BITS);

        changeState();
        setText();

        /*
        if (fishgoal <= boat.inventory.size()) text = "You have my fish \n [PRESS E to sell them]";
        else text = "I need " + fishgoal + " fish. You have only " + boat.inventory.size() + " fish";

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            resume();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if (fishgoal <= boat.inventory.size()) {
                fishBool = true;
                text = "danke for fish";
            } else {
                fishBool = false;
                text = "get more fish, u need " + fishgoal + ", you have " + boat.inventory.size();
            }
        }

         */
        batch.begin();


        font.getData().setScale(3, 3);
        batch.draw(shop, 2, 2, 1920, 1080);
        font.setColor(0, 0, 0, 1);
        font.draw(batch, text, 1920 / 2 - 500, 275);

        batch.end();
    }

    void removeFish() {
        for (int i = boat.inventory.size() - 1; i > 0; i--) {
            boat.inventory.remove(boat.inventory.get(i));
        }
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

    // TODO MOVE TO ENUM
    public void changeState() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if (state == 0) {
                state = 1;
            } else if (state == 1) {
                if (fishgoal <= boat.inventory.size()) {
                    state = 2;
                    removeFish();
                    fishgoal = (int) Math.round(Math.random() * 14);
                } else state = 3;
            } else if (state == 3 || state == 2) state = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            resume();
        }
    }

    public void setText() {
        if (state == 0) {
            text = "Welcome to my shop! I am FISHMAN and I desire FISH. \n [Current amount of fish needed "
                    + fishgoal + "]" + "\n [press E to continue  press Q to exit]";
        }
        if (state == 1) {
            text = "Do you want to sell me " + fishgoal + " fish \n [Press E to sell  press Q to exit]";
        }
        if (state == 2) {
            text = "THANK YOU FOR THE FISH KIND SIR!!! \n [press E to continue  press Q to exit]";
        }
        if (state == 3) {
            text = "You don't have enough fish. You have " + boat.inventory.size() + " fish I need " + fishgoal + " fish \n [press E to continue  press Q to exit]";
        }
    }
}

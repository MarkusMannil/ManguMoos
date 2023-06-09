package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.moos.GeimClass;

public class TutorialScreen implements Screen {

    Texture tutorial;
    GeimClass geimClass;
    Batch batch;


    public TutorialScreen(GeimClass geimClass) {
        this.geimClass = geimClass;
    }

    @Override
    public void show() {
        tutorial = new Texture("images/tutorial.png");
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_ALPHA_BITS);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            geimClass.setScreen(geimClass.worldScreen);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        batch.begin();
        batch.draw(tutorial, 0, 0, 1920, 1080);
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

    }
}

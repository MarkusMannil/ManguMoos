package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.moos.GeimClass;


public class TitleScreen implements Screen {

    Texture exitButtonActive;
    Texture exitButtonInactive;

    Texture playButtonActive;
    Texture playButtonInactive;

    Batch batch;
    GeimClass geimClass;
    TransitionScreen transitionScreen;
    Stage stage;


    private static final int BUTTON_WIDTH = 80 * 8;
    private static final int BUTTON_HEIGHT = 34 * 8;

    public TitleScreen(GeimClass geimClass) {
        this.geimClass = geimClass;
    }

    @Override
    public void show() {

        //placeholder buttons for now
        playButtonActive = new Texture("assets/buttons/ilusStartNuppHover.png");
        exitButtonActive = new Texture("assets/buttons/ilusEndNuppHover.png");

        playButtonInactive = new Texture("buttons/ilusStartNupp.png");
        exitButtonInactive = new Texture("buttons/ilusEndNupp.png");

        batch = new SpriteBatch();
        stage = new Stage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int WINDOW_WIDTH = (1920 / 2);
        int WINDOW_HEIGHT = (1080 / 2);

        batch.begin();

        if (Gdx.input.getX() > WINDOW_WIDTH - BUTTON_WIDTH / 2 - 30 && Gdx.input.getX() < WINDOW_WIDTH + BUTTON_WIDTH / 2 - 30 &&
                Gdx.input.getY() > WINDOW_HEIGHT - BUTTON_HEIGHT / 2 - 30 * 5 && Gdx.input.getY() < WINDOW_HEIGHT + BUTTON_HEIGHT / 2 - 30 * 5) {
            batch.draw(playButtonActive, WINDOW_WIDTH - BUTTON_WIDTH / 2, WINDOW_HEIGHT - BUTTON_HEIGHT / 2 + 120, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                geimClass.setScreen(geimClass.worldScreen);
            }
        } else {
            batch.draw(playButtonInactive, WINDOW_WIDTH - BUTTON_WIDTH / 2, WINDOW_HEIGHT - BUTTON_HEIGHT / 2 + 120, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        if (Gdx.input.getX() > WINDOW_WIDTH - BUTTON_WIDTH / 2 - 30 && Gdx.input.getX() < WINDOW_WIDTH + BUTTON_WIDTH / 2 - 30 &&
                Gdx.input.getY() > WINDOW_HEIGHT + 10 && Gdx.input.getY() < WINDOW_HEIGHT + 200) {
            batch.draw(exitButtonActive, WINDOW_WIDTH - BUTTON_WIDTH / 2, WINDOW_HEIGHT - 250, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            batch.draw(exitButtonInactive, WINDOW_WIDTH - BUTTON_WIDTH / 2, WINDOW_HEIGHT - 250, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
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

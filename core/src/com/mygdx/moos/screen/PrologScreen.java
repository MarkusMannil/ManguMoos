package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.moos.GeimClass;

public class PrologScreen implements Screen {

    BitmapFont font = new BitmapFont();
    GeimClass geimClass;
    Batch batch;
    Camera camera;

    public PrologScreen(GeimClass geimClass){this.geimClass = geimClass;}
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera = new Camera() {
            @Override
            public void update() {

            }

            @Override
            public void update(boolean updateFrustum) {

            }
        };
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.draw(batch, "fuck you", 500,500);

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

    //slideshow for GameTheory lore video
}

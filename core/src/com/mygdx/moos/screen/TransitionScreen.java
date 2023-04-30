package com.mygdx.moos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.moos.GeimClass;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TransitionScreen implements Screen {

    //this is the fade in/out transition scene inbetween gamess and menus
    private Screen currentScreen;
    private Screen nextScreen;


    public GeimClass geimClass;

    // Once this reaches 1.0f the next scene is shown
    private float alpha = 0f;
    // true if fade in, false if fade out
    private boolean fadeDirection = true;

    public TransitionScreen(Screen current, Screen next, GeimClass geimClass) {
        this.currentScreen = current;
        this.nextScreen = next;

        // I temporarily change the screen to next because if I call render() on it without calling the create() function
        // there will be crashed caused by using null variables
        //geimClass.setScreen(next);
        //geimClass.setScreen(current);


        this.geimClass = geimClass;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        if (fadeDirection == true) {
            currentScreen.render(Gdx.graphics.getDeltaTime());
        } else {
            nextScreen.render(Gdx.graphics.getDeltaTime());
        }

        Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl.GL_SRC_ALPHA, Gdx.gl.GL_ONE_MINUS_SRC_ALPHA);
        geimClass.shapeRenderer.setColor(1, 1, 1, alpha);
        geimClass.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        geimClass.shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        geimClass.shapeRenderer.end();
        Gdx.gl.glDisable(Gdx.gl.GL_BLEND);

        if (alpha >= 1) {
            fadeDirection = false;
        }
        else if (alpha <= 0 && fadeDirection == false) {
            geimClass.setScreen(nextScreen);
        }
        alpha += fadeDirection == true ? 0.5 : -0.5;
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

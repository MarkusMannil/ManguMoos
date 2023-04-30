package com.mygdx.moos.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class MerchantScreen implements Screen {
    //this for accepting delivery missions and selling merchandise
    Texture shop;
    Texture button;

    @Override
    public void show() {

        shop = new Texture("assests/shop.png");

    }

    @Override
    public void render(float delta) {

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

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

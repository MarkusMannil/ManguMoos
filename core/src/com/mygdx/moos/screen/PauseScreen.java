package com.mygdx.moos.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.moos.GeimClass;

public class PauseScreen implements Screen {

    public PauseScreen(GeimClass geimClass)  {
        this.geimClass = geimClass;
    }

    Texture resumeActive;
    Texture resumeInactive;

    Texture menuActive;
    Texture menuInactive;

    Batch batch;
    GeimClass geimClass;

    private static final int BUTTON_WIDTH = 80*7;
    private static final int BUTTON_HEIGHT = 34*7;

    @Override
    public void show() {
        resumeActive = new Texture("assets/buttons/continue.png");
        resumeInactive = new Texture("assets/buttons/continueHover.png");

        menuActive = new Texture("assets/buttons/mainMenu.png");
        menuInactive = new Texture("assets/buttons/mainMenuHover.png");

        batch = new SpriteBatch();

        System.out.println(geimClass.gameScreen.player.playerX + " " + geimClass.gameScreen.player.playerY + " YES");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_ALPHA_BITS);

        int WINDOW_WIDTH = (1920/2);
        int WINDOW_HEIGHT = (1080/2);

        batch.begin();

        if(Gdx.input.getX()>WINDOW_WIDTH-BUTTON_WIDTH/2 && Gdx.input.getX()<WINDOW_WIDTH+BUTTON_WIDTH/2
                && Gdx.input.getY()>WINDOW_HEIGHT-BUTTON_HEIGHT/2 && Gdx.input.getY()<WINDOW_HEIGHT+BUTTON_HEIGHT/2){
            batch.draw(resumeInactive, WINDOW_WIDTH-BUTTON_WIDTH/2, WINDOW_HEIGHT-BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                System.out.println(geimClass.gameScreen.player.playerX + " " + geimClass.gameScreen.player.playerY + " YES");
                geimClass.setScreen(geimClass.gameScreen);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            batch.draw(resumeActive, WINDOW_WIDTH-BUTTON_WIDTH/2, WINDOW_HEIGHT-BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }



        if(Gdx.input.getX()>WINDOW_WIDTH-BUTTON_WIDTH/2 && Gdx.input.getX()<WINDOW_WIDTH+BUTTON_WIDTH/2
        && Gdx.input.getY()>WINDOW_HEIGHT-BUTTON_HEIGHT/2+300 && Gdx.input.getY()<WINDOW_HEIGHT+BUTTON_HEIGHT/2+300){
            batch.draw(menuInactive, WINDOW_WIDTH-BUTTON_WIDTH/2, WINDOW_HEIGHT-BUTTON_HEIGHT/2-300, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                geimClass.setScreen(new TitleScreen(geimClass));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            batch.draw(menuActive, WINDOW_WIDTH-BUTTON_WIDTH/2, WINDOW_HEIGHT-BUTTON_HEIGHT/2-300, BUTTON_WIDTH, BUTTON_HEIGHT);
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
        System.out.println(geimClass.gameScreen);
        //geimClass.setScreen(geimClass.gameScreen);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //hajutatud pause screen koos "exit to menu" ja "continue" nupuga
}

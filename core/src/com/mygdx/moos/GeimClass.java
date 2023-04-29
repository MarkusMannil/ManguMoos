package com.mygdx.moos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.screen.GameScreen;
import com.mygdx.moos.screen.PauseScreen;
import com.mygdx.moos.screen.TitleScreen;
import com.mygdx.moos.screen.WorldScreen;

public class GeimClass extends Game {
	public SpriteBatch batch;
	Texture img;
	public GameScreen gameScreen;
	public TitleScreen titlescreen;
	public PauseScreen pauseScreen;
	public WorldScreen worldScreen;

	@Override
	public void create() {
		pauseScreen = new PauseScreen(this);
		titlescreen = new TitleScreen(this);
		worldScreen = new WorldScreen(this);
		gameScreen = new GameScreen(this);


		setScreen(titlescreen);
		//setScreen(worldScreen);
	}
}

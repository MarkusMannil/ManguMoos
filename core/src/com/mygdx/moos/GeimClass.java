package com.mygdx.moos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.screen.*;

public class GeimClass extends Game {
	public SpriteBatch batch;
	Texture img;
	public GameScreen gameScreen;
	public TitleScreen titlescreen;
	public PauseScreen pauseScreen;
	public WorldScreen worldScreen;
	public PrologScreen prologScreen;
	public BoatFightScreen boatFightScreen;

	@Override
	public void create() {
		pauseScreen = new PauseScreen(this);
		titlescreen = new TitleScreen(this);
		worldScreen = new WorldScreen(this);
		gameScreen = new GameScreen(this);
		shapeRenderer = new ShapeRenderer();
		prologScreen = new PrologScreen(this);
		//boatFightScreen = new BoatFightScreen(this);

		setScreen(titlescreen);
		//setScreen(worldScreen);
		//setScreen(prologScreen);
		//setScreen(boatFightScreen);

	}
}

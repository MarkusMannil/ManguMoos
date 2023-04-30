package com.mygdx.moos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.moos.screen.*;

public class GeimClass extends Game {
	public ShapeRenderer shapeRenderer;
	public GameScreen gameScreen;
	public TitleScreen titlescreen;
	public PauseScreen pauseScreen;
	public WorldScreen worldScreen;
	public PrologScreen prologScreen;
	public BoatFightScreen boatFightScreen;
	public TutorialScreen tutorialScreen;

	@Override
	public void create() {
		pauseScreen = new PauseScreen(this);
		titlescreen = new TitleScreen(this);
		worldScreen = new WorldScreen(this);
		gameScreen = new GameScreen(this);
		shapeRenderer = new ShapeRenderer();
		tutorialScreen = new TutorialScreen(this);
		prologScreen = new PrologScreen(this);

		//boatFightScreen = new BoatFightScreen(this);

		setScreen(titlescreen);
		//setScreen(worldScreen);
		//setScreen(prologScreen);
		//setScreen(boatFightScreen);

	}
}

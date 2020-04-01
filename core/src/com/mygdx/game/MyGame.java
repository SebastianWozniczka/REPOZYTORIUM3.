package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.GamePlay;

public class MyGame extends Game {
	public SpriteBatch batch;
	public static AssetManager manager;
	private boolean paused;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager=new AssetManager();
		manager.load("sounds/intro.mp3", Music.class);
		manager.load("sounds/menu.mp3", Music.class);
		manager.load("sounds/ooo.mp3", Music.class);
		manager.load("sounds/chod.mp3", Music.class);
		manager.load("sounds/sex.mp3", Music.class);
		manager.load("sounds/skok.mp3", Music.class);


		manager.finishLoading();

		this.setScreen(new GamePlay(this));
	}

	@Override
	public void render () {
		super.render();

	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isPaused() {
		return paused;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

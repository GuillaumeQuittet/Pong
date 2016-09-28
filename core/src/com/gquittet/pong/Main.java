package com.gquittet.pong;

import com.badlogic.gdx.Game;
import com.gquittet.pong.assets.AssetLoader;
import com.gquittet.pong.logs.Log;
import com.gquittet.pong.screens.GameScreen;

public class Main extends Game {

	@Override
	public void create() {
		Log.info("Game created.");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
}

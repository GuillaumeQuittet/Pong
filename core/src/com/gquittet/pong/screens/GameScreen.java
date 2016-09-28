package com.gquittet.pong.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.gquittet.pong.assets.AssetLoader;
import com.gquittet.pong.helpers.InputHandler;
import com.gquittet.pong.logs.Log;
import com.gquittet.pong.renderer.Renderer;

public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private Renderer renderer;

    public GameScreen() {
        Log.info("Game screen attached");
        gameWorld = new GameWorld();
        renderer = new Renderer(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler(gameWorld.getBatLeft(), gameWorld.getBatRight()));
    }

    @Override
    public void show() {
        Log.info("Show called");
    }

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        Log.info("Resizing");
    }

    @Override
    public void pause() {
        Log.info("Pause called");
    }

    @Override
    public void resume() {
        Log.info("Resume called");
    }

    @Override
    public void hide() {
        Log.info("Hide called");
    }

    @Override
    public void dispose() {
        Log.info("Dispose called");
        AssetLoader.dispose();
    }

}

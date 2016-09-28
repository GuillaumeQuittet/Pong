package com.gquittet.pong.renderer;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gquittet.pong.assets.AssetLoader;
import com.gquittet.pong.logs.Log;
import com.gquittet.pong.objects.Ball;
import com.gquittet.pong.objects.Bat;
import com.gquittet.pong.objects.ScoreBoard;
import com.gquittet.pong.screens.GameWorld;

import static com.badlogic.gdx.Gdx.gl;

public class Renderer {

    private GameWorld gameWorld;

    // The camera
    private OrthographicCamera cam;
    // The ShapeRenderer
    private ShapeRenderer shapeRenderer;
    // The sprite batch
    private SpriteBatch spriteBatch;

    // The object
    private Bat batLeft;
    private Bat batRight;
    private Ball ball;

    // The texture
    private Texture background;

    // The score
    private ScoreBoard scoreBoard;

    public Renderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        // Make the camera
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 160, 144);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);
        initGameObjects();
        initTextures();
    }

    private void initGameObjects() {
        batLeft = gameWorld.getBatLeft();
        batRight = gameWorld.getBatRight();
        ball = gameWorld.getBall();
        scoreBoard = gameWorld.getScoreBoard();
    }

    private void initTextures() {
        background = AssetLoader.background;
    }

    private void drawBackground() {
        // The background
        spriteBatch.begin();
        spriteBatch.disableBlending();
        spriteBatch.draw(background, 0, 0, 160, 144);
        spriteBatch.end();
    }

    private void drawBats() {
        // The left bat
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(batLeft.getPosition().x, batLeft.getPosition().y,
                batLeft.getWidth(),batLeft.getHeight());
        shapeRenderer.end();
        // The right bat
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(batRight.getPosition().x, batRight.getPosition().y,
                batRight.getWidth(), batRight.getHeight());
        shapeRenderer.end();
    }

    private void drawBall() {
        // The ball
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(ball.getPosition().x, ball.getPosition().y,
                ball.getWidth(),ball.getHeight());
        shapeRenderer.end();
    }

    private void drawScoreBoard() {
        spriteBatch.begin();
        spriteBatch.enableBlending();
        AssetLoader.textFont.draw(spriteBatch, Integer.toString(scoreBoard.getBatLeftScore()), 28, 10);
        AssetLoader.textFont.draw(spriteBatch, Integer.toString(scoreBoard.getBatRightScore()), 114, 10);
        spriteBatch.disableBlending();
        spriteBatch.end();
    }

    private void drawBoundingShape() {
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(ball.getBoundingRectangle().x, ball.getBoundingRectangle().y,
                ball.getBoundingRectangle().width, ball.getBoundingRectangle().height);
        shapeRenderer.rect(batLeft.getBoundingRectangle().x, batLeft.getBoundingRectangle().y,
                batLeft.getBoundingRectangle().width, batLeft.getBoundingRectangle().height);
        shapeRenderer.rect(batRight.getBoundingRectangle().x, batRight.getBoundingRectangle().y,
                batRight.getBoundingRectangle().width, batRight.getBoundingRectangle().height);
        shapeRenderer.end();
    }

    public void render() {
        Log.info("render");
        // Clean the screen
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        // Draw the objects
        drawBackground();
        drawBats();
        drawBall();
        drawScoreBoard();
        drawBoundingShape();
    }

}

package com.gquittet.pong.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.gquittet.pong.assets.AssetLoader;
import com.gquittet.pong.helpers.Collisions;
import com.gquittet.pong.logs.Log;
import com.gquittet.pong.objects.Ball;
import com.gquittet.pong.objects.Bat;
import com.gquittet.pong.objects.ScoreBoard;

public class GameWorld {

    private Bat batLeft;
    private Bat batRight;
    private Ball ball;
    private float gameWidth;
    private int wallCount;
    private float speed;
    private int lastBatID;

    // The scoreboard
    private ScoreBoard scoreBoard;

    public GameWorld() {
        int batWidth = 5;
        int batHeight = 20;
        gameWidth = 160;
        batLeft = new Bat(batWidth, batHeight, new Vector2(0, 0), 1);
        batRight = new Bat(batWidth, batHeight, new Vector2(gameWidth - batWidth, 0), 2);
        speed = 2f;
        ball = new Ball(5, 5, new Vector2(0, 0), speed);
        scoreBoard = new ScoreBoard();
        lastBatID = 0;
        start();
    }

    private void start() {
        if (scoreBoard.getBatLeftScore() > 9 || scoreBoard.getBatRightScore() > 9)
            Log.fatal("Fin du jeu");
        wallCount = 0;
        if (lastBatID == 1)
            ball.setDirection(speed, 0);
        else if (lastBatID == 2)
            ball.setDirection(-speed, 0);
        lastBatID = 0;
        ball.setPosition(new Vector2((gameWidth - ball.getWidth())/2, (gameWidth - ball.getWidth())/2));
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ball.setPause(false);
            }
        }, 2f);
    }

    public void update(float delta) {
        batLeft.update(delta);
        batRight.update(delta);
        ball.update(delta);
        collides();
    }

    private void collides() {
        if (Collisions.collides(ball, batLeft)) {
            ball.setPosition(new Vector2(batLeft.getPosition().x + batLeft.getWidth(), ball.getPosition().y));
            ball.computeDirection(batLeft);
            AssetLoader.pongBat.play();
            wallCount = 0;
            lastBatID = batLeft.getId();
        } else if (Collisions.collides(ball, batRight)) {
            ball.setPosition(new Vector2(batRight.getPosition().x - ball.getWidth(), ball.getPosition().y));
            ball.computeDirection(batRight);
            AssetLoader.pongBat.play();
            wallCount = 0;
            lastBatID = batRight.getId();
        } else if ((ball.getPosition().y <= 0 || ball.getPosition().y + ball.getHeight() >= 144) &&
                (ball.getPosition().x > 0 || ball.getPosition().x + ball.getWidth() < 144)) {
            ball.setDirection(ball.getDirection().x, -ball.getDirection().y);
            AssetLoader.pongWall.play();
            wallCount++;
            if (wallCount >= 3) {
                if (lastBatID == 1)
                    ball.setDirection(ball.getDirection().x + 2, ball.getDirection().y);
                else if (lastBatID == 2)
                    ball.setDirection(ball.getDirection().x - 2, ball.getDirection().y);
                wallCount = 0;
            }
        } else if (ball.getPosition().x <= 0) {
                scoreBoard.increaseBatRightScore();
            ball.setPause(true);
            start();
        } else if (ball.getPosition().x >= 160) {
            scoreBoard.increaseBatLeftScore();
            ball.setPause(true);
            start();
        }
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public Bat getBatLeft() {
        return batLeft;
    }

    public Bat getBatRight() {
        return batRight;
    }

    public Ball getBall() {
        return ball;
    }

    public int getLastBatID() {
        return lastBatID;
    }

    public void setLastBatID(int lastBatID) {
        this.lastBatID = lastBatID;
    }
}

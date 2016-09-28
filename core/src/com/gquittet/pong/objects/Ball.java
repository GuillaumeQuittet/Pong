package com.gquittet.pong.objects;

import com.badlogic.gdx.math.Vector2;
import com.gquittet.pong.logs.Log;

/**
 * The class of the ball
 * @author Guillaume Quittet
 *
 */
public class Ball extends Object {

    private float speed;
    private Vector2 direction;
    private boolean goToTheRight;
    private boolean pause;

    /**
     * Make the ball
     * @param width - The width
     * @param height - The height
     * @param position - The position of the up corner
     * @param speed - The speed
     * @param goToTheRight - The condition to go to the right
     */
    public Ball(int width, int height, Vector2 position, float speed, boolean goToTheRight) {
        super(width, height, position);
        this.speed = speed;
        direction = new Vector2(1, 0);
        this.goToTheRight = goToTheRight;
        this.pause = false;
    }

    public void update(float delta) {
        if(!pause) {
            if(goToTheRight)
                setPosition(new Vector2(getPosition().x + direction.x, getPosition().y - direction.y));
            else
                setPosition(new Vector2(getPosition().x - direction.x, getPosition().y - direction.y));
            float ballSpeed = (float) Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
            Log.info("update the ball, speed: " + ballSpeed + " pixels/sec");
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(float x, float y) {
        direction.x = x;
        direction.y = y;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public boolean isGoToTheRight() {
        return goToTheRight;
    }

    public void setGoToTheRight(boolean goToTheRight) {
        this.goToTheRight = goToTheRight;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}

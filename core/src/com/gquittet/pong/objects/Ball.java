package com.gquittet.pong.objects;

import com.badlogic.gdx.math.Vector2;
import com.gquittet.pong.helpers.Collisions;
import com.gquittet.pong.logs.Log;

/**
 * The class of the ball
 * @author Guillaume Quittet
 *
 */
public class Ball extends Object {

    private float speed;
    private Vector2 direction;
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
        direction = new Vector2(speed, 0);
        this.pause = false;
    }

    public void update(float delta) {
        if(!pause) {
            setPosition(new Vector2(getPosition().x + direction.x, getPosition().y - direction.y));
            float ballSpeed = (float) Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
            Log.info("update the ball, speed: " + ballSpeed + " pixels/sec");
        }
    }

    public void computeDirection(Bat bat) {
        // Make a point with the bat Y center
        float batCenterX = 0;
        if (bat.getId() == 1)
            batCenterX = 0f;
        else if (bat.getId() == 2)
            batCenterX = 160f;
        float batCenterY = bat.getPosition().y + bat.getHeight() / 2;
        Vector2 a = new Vector2(batCenterX, batCenterY);
        // Make a point with the ball center
        float ballCenterX = getPosition().x + getWidth() / 2;
        float ballCenterY = getPosition().y + getHeight() / 2;
        Vector2 b = new Vector2(ballCenterX, ballCenterY);
        // Get the line slope
        float slope = 0;
        if (bat.getId() == 1)
            slope = Collisions.computeSlope(a, b);
        else if (bat.getId() == 2)
            slope = Collisions.computeSlope(b, a);
        // Get the angle from the slope: slope = tan(theta)
        float theta = (float) Math.toDegrees(Math.atan(slope));
        // Compute the B vertices location: B (X, Y) and A is (0, 0)
        if (bat.getId() == 1)
            direction = new Vector2((float) (speed * Math.cos(theta)), (float) (speed * Math.sin(theta)));
        else if (bat.getId() == 2)
            direction = new Vector2((float) (-speed * Math.cos(theta)), (float) (speed * Math.sin(theta)));
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

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}

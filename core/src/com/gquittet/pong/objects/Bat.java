package com.gquittet.pong.objects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.gquittet.pong.helpers.Collisions;
import com.gquittet.pong.logs.Log;

/**
 * This class make the bat that is useful
 * for the pong game.
 * @author Guillaume Quittet
 */
public class Bat extends Object {

    public boolean canUpdate;
    private boolean yDown;

    /**
     * Make the bat
     * @param width - The width of the bat
     * @param height - The height of the bat
     * @param position - The position of the bat
     */
    public Bat(int width, int height, Vector2 position) {
        super(width, height, position);
        yDown = true;
        canUpdate = false;
    }

    /**
     * This function compute the move of the bat.
     * @param delta - The delta of the game.
     */
    public void update(float delta) {
        if(canUpdate) {
            if (yDown) {
                if (getPosition().y > 122) {
                    setPosition(new Vector2(getPosition().x, 124f));
                }
                else {
                    setPosition(new Vector2(getPosition().x, getPosition().y + 2));
                }
            }
            else {
                if (getPosition().y < 2) {
                    setPosition(new Vector2(getPosition().x, 0f));
                }
                else {
                    setPosition(new Vector2(getPosition().x, getPosition().y - 2));
                }
            }
            Log.info("update");
        }
    }

    public void computeDirection(Ball ball, boolean isLeftBat) {
        // Make a point with the bat Y center
        float batCenterX;
        if (isLeftBat)
            batCenterX = 0f;
        else
            batCenterX = 160f;
        float batCenterY = getPosition().y + getHeight()/2;
        Vector2 a = new Vector2(batCenterX, batCenterY);
        // Make a point with the ball center
        float ballCenterX = ball.getPosition().x + ball.getWidth()/2;
        float ballCenterY = ball.getPosition().y + ball.getHeight()/2;
        Vector2 b = new Vector2(ballCenterX, ballCenterY);
        // Get the line slope
        float slope = Collisions.computeSlope(a, b);
        // Get the angle from the slope: slope = tan(theta)
        float theta = (float) Math.toDegrees(Math.atan(slope));
        Log.info(Float.toString(theta));
        // Compute the B vertices location: B (X, Y) and A is (0, 0)
        ball.setDirection((float)(ball.getSpeed() * Math.cos(theta)), (float)(ball.getSpeed() * Math.sin(theta)));
        if(ball.isGoToTheRight())
            ball.setGoToTheRight(false);
        else
            ball.setGoToTheRight(true);
    }

    /**
     * This function is called when the user down a key.
     * @param keyCode The id of the key.
     */
    public void onClick(int keyCode) {
        if (keyCode == Input.Keys.UP) {
            yDown = false;
            canUpdate = true;
        }
        else if (keyCode == Input.Keys.DOWN) {
            yDown = true;
            canUpdate = true;
        }
    }
}

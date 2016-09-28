package com.gquittet.pong.helpers;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.gquittet.pong.objects.Ball;
import com.gquittet.pong.objects.Bat;

/**
 * Created by Guillaume QUITTET on 26-09-16.
 */
public class Collisions {

    public static float computeSlope(Vector2 a, Vector2 b) {
        float slope = (b.y - a.y) / (b.x - a.x);
        return slope;
    }

    private static boolean getCollides(Ball ball, Bat bat) {
        if((ball.getPosition().y <= bat.getPosition().y + bat.getHeight()) &&
                (ball.getPosition().y + ball.getHeight() >= bat.getPosition().y))
            return true;
        return false;
    }

    public static boolean collides(Ball ball, Bat bat, boolean isBatLeft) {
        boolean isBatRight = !isBatLeft;
        if((ball.getPosition().x <= bat.getPosition().x + bat.getWidth()) && isBatLeft)
            return getCollides(ball, bat);
        else if((ball.getPosition().x + ball.getWidth() >= bat.getPosition().x) && isBatRight)
            return getCollides(ball, bat);
        return false;
    }
}

package com.gquittet.pong.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Guillaume QUITTET on 26-09-16.
 */
public class Object {

    private int width, height;
    private Vector2 position;
    private Rectangle boundingRectangle;

    public Object(int width, int height, Vector2 position) {
        this.width = width;
        this.height = height;
        this.position = position;
        boundingRectangle = new Rectangle(position.x, position.y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        boundingRectangle.setPosition(position);
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void setBoundingRectangle(Rectangle boundingRectangle) {
        this.boundingRectangle = boundingRectangle;
    }
}

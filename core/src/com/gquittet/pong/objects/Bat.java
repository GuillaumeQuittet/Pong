package com.gquittet.pong.objects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.gquittet.pong.logs.Log;

/**
 * This class make the bat that is useful
 * for the pong game.
 * @author Guillaume Quittet
 */
public class Bat extends Object {

    public boolean canUpdate;
    private boolean yDown;
    private int id;

    /**
     * Make the bat
     * @param width - The width of the bat
     * @param height - The height of the bat
     * @param position - The position of the bat
     * @param id - The id of the bat
     */
    public Bat(int width, int height, Vector2 position, int id) {
        super(width, height, position);
        this.id = id;
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

    public int getId() {
        return id;
    }
}

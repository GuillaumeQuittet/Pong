package com.gquittet.pong.helpers;

import com.badlogic.gdx.InputProcessor;
import com.gquittet.pong.objects.Bat;

public class InputHandler implements InputProcessor {

    private Bat bat1;
    private Bat bat2;

    public InputHandler(Bat bat1, Bat bat2) {
        this.bat1 = bat1;
        this.bat2 = bat2;
    }

    @Override
    public boolean keyDown(int keycode) {
        bat1.onClick(keycode);
        bat2.onClick(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        bat1.canUpdate = false;
        bat2.canUpdate = false;
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}

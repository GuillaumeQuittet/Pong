package com.gquittet.pong.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.List;

public class AssetLoader {

    public static Texture background;
    public static Sound pongBat;
    public static Sound pongWall;
    public static BitmapFont textFont;
    private static List<Texture> texturesList = new ArrayList<Texture>();

    public static void load() {
        background = new Texture(Gdx.files.internal("background.png"));
        background.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        texturesList.add(background);

        pongBat = Gdx.audio.newSound(Gdx.files.internal("sounds/pong_bat.wav"));
        pongWall = Gdx.audio.newSound(Gdx.files.internal("sounds/pong_wall.wav"));

        textFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        textFont.getData().setScale(.25f, -.25f);
    }

    public static void dispose() {
        for(Texture texture : texturesList) {
            texture.dispose();
        }
    }

}

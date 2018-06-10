package com.cas.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Level {
    public TiledMap stage;
    public Level(String stagePath) {
        stage = new TmxMapLoader().load(stagePath);
    }
}

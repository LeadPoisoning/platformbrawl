package com.cas.game.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cas.game.model.Level;

public class LevelController {

    public static Level level;
    public static final float UNIT_SCALE = 1/16f;
    public static OrthogonalTiledMapRenderer renderer;
    public static Batch spriteBatch;
    public static World gameWorld;
    public static Array<Body> worldBodies;
    private static Box2DDebugRenderer debugRenderer;

    public static void initializeController() {
        level = new Level("stages/stage01.tmx");
        renderer = new OrthogonalTiledMapRenderer(level.stage, UNIT_SCALE);

        gameWorld = new World(new Vector2(0, -1),true);
        worldBodies = new Array<Body>();
        debugRenderer = new Box2DDebugRenderer();

        spriteBatch = renderer.getBatch();
    }

    public static void draw() {
        spriteBatch.begin();
        PlayerController.player.draw(spriteBatch);
        spriteBatch.end();
        debugRenderer.render(gameWorld, CameraController.camera.combined);
    }

    public static void update(float deltaTime) {
        renderer.setView(CameraController.camera);
        renderer.render();
        PlayerController.update(deltaTime);
        gameWorld.step(1/60f,1,1);
    }

    private static void updateWorldBodies() {
        worldBodies.clear();
    }
}

package com.cas.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cas.game.model.Player;

public class GameScreen implements Screen {

    public TiledMap stage;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;

    public Batch spriteBatch;
    public Player player;

    public static World gameWorld;
    private Box2DDebugRenderer debugRenderer;

    public GameScreen() {
        stage = new TmxMapLoader().load("stages/stage01.tmx");
        renderer = new OrthogonalTiledMapRenderer(stage, 1/16f);
        gameWorld = new World(new Vector2(0, -10),true);
        debugRenderer = new Box2DDebugRenderer();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(32f, 32f*h/w);
        camera.position.set(36, 32, 0);

        spriteBatch = renderer.getBatch();
        player = new Player(64,80);


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.75f, 0.89f, 0.93f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        player.update(delta);

        spriteBatch.begin();
        player.draw(spriteBatch);
        spriteBatch.end();

        debugRenderer.render(gameWorld, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 32f;
        camera.viewportHeight = 32f*height/width;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

package com.cas.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public SpriteSheet spriteSheet;
    public Animation<TextureRegion> animation;

    private float stateTime;
    private int width;
    private int height;

    public Player() {
        width = 64;
        height = 80;
        spriteSheet = new SpriteSheet("img/shad.png",width,height);
        animation = spriteSheet.getAnimation(0, 7);
        position = new Vector2(32f,28f);
        stateTime = 0f;

    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(animation.getKeyFrame(stateTime, true), position.x, position.y, width/16f, height/16f);
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
}

package com.cas.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.cas.game.view.GameScreen;

import java.util.HashMap;

public class Player {
    public Vector2 position;
    public SpriteSheet spriteSheet;
    public float width;
    public float height;
    public String currentAnimation;

    private float stateTime;
    private HashMap<String, Animation<TextureRegion>> animations;

    public Player(int width, int height) {
        this.width = width/16f;
        this.height = height/16f;
        spriteSheet = new SpriteSheet("img/shad.png",width,height);
        animations = new HashMap<String, Animation<TextureRegion>>();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body playerBody = GameScreen.gameWorld.createBody(bodyDef);
        playerBody.setUserData(this);

        // Attaches rectangular fixture to sprite
        PolygonShape rectShape = new PolygonShape();
        rectShape.setAsBox(this.width/2,this.height/2,new Vector2(this.width/2,this.height/2), 0f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectShape;
        playerBody.createFixture(fixtureDef);

        rectShape.dispose();


        animations.put("walkRight", spriteSheet.getAnimation(0, 7, 12));
        animations.put("walkLeft", spriteSheet.flipAnimation(animations.get("walkRight"), true, false));
        currentAnimation = "walkLeft";

        position = new Vector2(32f,28f);
        stateTime = 0f;

    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(animations.get(currentAnimation).getKeyFrame(stateTime, true), position.x, position.y, width, height);
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
}

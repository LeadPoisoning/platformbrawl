package com.cas.game.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.cas.game.controller.LevelController;

public class Player extends Sprite {


    public Player(Vector2 position, int width, int height) {
        super(position, width, height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        physicsBody = LevelController.gameWorld.createBody(bodyDef);
        physicsBody.setUserData(this);

        // Attaches rectangular fixture to sprite
        PolygonShape rectShape = new PolygonShape();
        rectShape.setAsBox(this.width/2f,this.height/2f, new Vector2(this.width/2f,this.height/2f), 0f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectShape;
        physicsBody.createFixture(fixtureDef);

        rectShape.dispose();


        animations.put("walkRight", spriteSheet.getAnimation(0, 7, 12));
        animations.put("walkLeft", spriteSheet.flipAnimation(animations.get("walkRight"), true, false));
        currentAnimation = "walkRight";

    }

    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }

    public void update(float deltaTime) {
        if (this.physicsBody.getLinearVelocity().x > 0) {
            currentAnimation = "walkRight";
        } else if (this.physicsBody.getLinearVelocity().x < 0) {
            currentAnimation = "walkLeft";
        }
        super.update(deltaTime);
    }
}

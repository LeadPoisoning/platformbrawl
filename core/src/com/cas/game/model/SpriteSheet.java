package com.cas.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {

    public TextureRegion[] spriteFrames;
    public Animation <TextureRegion> animation;
    public Texture spriteSheet;

    public SpriteSheet(String pathToFile, int w, int h) {

        int spriteCount = 0;
        spriteSheet = new Texture(Gdx.files.internal(pathToFile));
        TextureRegion[][] spriteSheetFrames = TextureRegion.split(spriteSheet,w,h);

        for(int i = 0; i < spriteSheetFrames.length; i++) {
            for (int j = 0; j < spriteSheetFrames[i].length; j++) {
                spriteCount++;
            }
        }

        spriteFrames = new TextureRegion[spriteCount];
        spriteCount = 0;

        for(int i =0; i < spriteSheetFrames.length; i++) {
            for (int j = 0; j < spriteSheetFrames[i].length; j++) {
                spriteFrames[spriteCount++] = spriteSheetFrames[i][j];
            }
        }
    }

    public Animation<TextureRegion> getAnimation(int startFrame, int endFrame) {
        TextureRegion[] animationFrames = new TextureRegion[endFrame - startFrame + 1];
        for (int i = startFrame; i <= endFrame; i++) {
            animationFrames[i] = spriteFrames[i];
        }
        animation = new Animation<TextureRegion>(1/16f, animationFrames);

        return animation;
    }
}

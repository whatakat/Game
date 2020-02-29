package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.math.Rect;

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;

    public Sprite(TextureRegion region){
        if (region == null){
            throw  new NullPointerException();
        }
        regions = new TextureRegion[1];
        regions[0]=region;
    }
    public void draw(SpriteBatch batch){
        batch.draw(regions[frame],getLeft(),getBottom(),halfWidth,halfHeight,getWidth(),getHeight(),
                scale,scale,angle);
    }
}

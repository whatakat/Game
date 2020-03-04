package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.sprite.Arrow;

public class Ship extends Sprite {
    protected Vector2 v = new Vector2();
    protected Rect worldBounds;

    protected ArrowPool arrowPool;
    protected TextureRegion arrowRegion;

    protected final Vector2 arrowV = new Vector2();
    protected float arrowHeight;
    protected int arrowDamage;

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot(){
        Arrow arrow = arrowPool.obtain();
        arrow.set(this,arrowRegion,pos, arrowV, arrowHeight,worldBounds,arrowDamage);
    }
}

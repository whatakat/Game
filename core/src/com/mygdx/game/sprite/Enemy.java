package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;

public class Enemy extends Ship {

    private Vector2 v0 = new Vector2();
    public Enemy(ArrowPool arrowPool, Rect worldBounds) {
        super(arrowPool, worldBounds);
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}

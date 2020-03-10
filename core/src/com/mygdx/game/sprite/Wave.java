package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

public class Wave extends Sprite {
    private Vector2 v = new Vector2();
    private Rect worldBounds;

    public Wave(TextureRegion region, float vx, float vy, float height) {
        super(region);
        v.set(vx,vy);
        setHeightProportion(height);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,-delta);
        checkAndHandleBounds();
    }
    private void checkAndHandleBounds(){
        if (getRight()<worldBounds.getLeft())setLeft(worldBounds.getRight());
        if (getLeft()>worldBounds.getRight())setRight(worldBounds.getLeft());
        if (getTop()<worldBounds.getBottom())setBottom(worldBounds.getTop());
        if (getBottom()>worldBounds.getTop()) setTop(worldBounds.getBottom());
    }
}

package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

public class WaveBg2 extends Sprite {
    private Vector2 v = new Vector2();
    private Rect worldBounds;

    public WaveBg2(TextureRegion region, float vx, float vy, float height) {
        super(region);
        v.set(vx,vy);
        setHeightProportion(height);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = 0;//Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = worldBounds.getTop()-0.1f;
        pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,-delta);
        checkAndHandleBounds();
    }
    private void checkAndHandleBounds(){
//        if (getRight()<worldBounds.getLeft())setLeft(worldBounds.getRight());// it's for moving to the left or right
//        if (getLeft()>worldBounds.getRight())setRight(worldBounds.getLeft());
        if (getTop()<worldBounds.getBottom())setBottom(worldBounds.getTop());
        if (getBottom()>worldBounds.getTop())setTop(worldBounds.getBottom());
    }
}

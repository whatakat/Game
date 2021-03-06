package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

public class WaveBg extends Sprite {
    private Vector2 v = new Vector2();
    private Rect worldBounds;

    public WaveBg(TextureRegion region, float vx, float vy, float height) {
        super(region);
        v.set(vx,vy);
        setHeightProportion(height);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = 0;//Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = 0;
        pos.set(posX,posY);
    }
    public void resize(Rect worldBounds, float y) {
        this.worldBounds = worldBounds;
        float posX = 0;//Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = 0+y;
        pos.set(posX,posY);
    }


    @Override
    public void update(float delta) {
        pos.mulAdd(v,-delta);
        checkAndHandleBounds();
    }
    private void checkAndHandleBounds(){
        if (getTop()<worldBounds.getBottom())setBottom(worldBounds.getTop());
    }
}

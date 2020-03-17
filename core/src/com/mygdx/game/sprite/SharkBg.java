package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

public class SharkBg extends Sprite {
    private Vector2 v = new Vector2();
    private Rect worldBounds;

    public SharkBg(TextureRegion region, float vx, float vy, float height) {
        super(region);
        v.set(vx,vy);
        setHeightProportion(height*1.02f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX =worldBounds.pos.x;
        float posY = worldBounds.pos.y-getRight();
        pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,-delta);
        checkAndHandleBounds();
    }
    private void checkAndHandleBounds(){
        if (getTop()<worldBounds.getBottom())setBottom(worldBounds.pos.y-getHalfWidth()-getRight());
    }
}

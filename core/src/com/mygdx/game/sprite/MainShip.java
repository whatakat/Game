package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;

public class MainShip extends Ship {
    float lowEnginePower;
    float currentEnginePower;
    float maxEnginePower;

    private static final float SHIP_HEIGHT = 0.2f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private Vector2 v = new Vector2();
    private Rect worldBounds;

    public MainShip(TextureAtlas atlas, ArrowPool arrowPool) {
        super(atlas.findRegion("Hero5"));
        setHeightProportion(SHIP_HEIGHT);
        this.arrowPool = arrowPool;
        this.arrowRegion = atlas.findRegion("gArrow");
        this.arrowHeight = 0.01f;
        this.arrowV.set(0,0.5f);
        this.arrowDamage = 1;

    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom()+BOTTOM_MARGIN);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,delta);
        v.scl(0.1f);
        if (InputHandler.isJustTouched()){
            currentEnginePower = lowEnginePower;
        }

        if (InputHandler.isTouched()){
            float tx = InputHandler.getX();
            float ty = InputHandler.getY();
            currentEnginePower+=100*delta;
            if (currentEnginePower>maxEnginePower)currentEnginePower = maxEnginePower;
            if (tx<1000f&&ty<400f){
                v.add(-0.2f,0f);
            }else if (tx>1000f&&ty<400f) {
                v.add(0.2f, 0f);
            }else{
                shoot();
            }

        }
        checkAndHandleBounds();

    }
    private void checkAndHandleBounds(){
        if (getRight()<worldBounds.getLeft()+0.1f)setRight(worldBounds.getLeft()+0.1f);
        if (getLeft()>worldBounds.getRight()-0.1f)setLeft(worldBounds.getRight()-0.1f);
//        if (getTop()<worldBounds.getBottom())setBottom(worldBounds.getTop());
//        if (getBottom()>worldBounds.getTop())setTop(worldBounds.getBottom());// it's could be interested
    }
}

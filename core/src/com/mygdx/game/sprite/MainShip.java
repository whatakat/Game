package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.pools.ExplosionPool;

public class MainShip extends Ship {
    float lowEnginePower;
    float currentEnginePower;
    float maxEnginePower;

    private static final float SHIP_HEIGHT = 0.16f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private Vector2 v = new Vector2();
    private Rect worldBounds;
    /**
     * param reloadInterval for auto shooting
     * */
    public MainShip(TextureAtlas atlas,Rect worldBounds, ArrowPool arrowPool, ExplosionPool explosionPool, Sound sound) {
        super(atlas.findRegion("hero"),1,2,2,sound,worldBounds);
        setHeightProportion(SHIP_HEIGHT);
        this.arrowPool = arrowPool;
        this.arrowRegion = atlas.findRegion("gArrow");
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        setNewGame();

    }
    public void setNewGame(){
        pos.x = worldBounds.pos.x;
        this.arrowHeight = 0.07f;
        this.arrowV.set(0,1.5f);
        this.arrowDamage = 1;
        this.reloadInterval = 1000f;
        this.hp = 10;
        flushDestroy();
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom()+BOTTOM_MARGIN);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
        reloadTimer +=delta;
        if (reloadTimer>=reloadInterval){
            reloadTimer = 0f;
            shoot();

        }
        v.scl(0.1f);
        if (InputHandler.isJustTouched()){
            currentEnginePower = lowEnginePower;
            shoot();
        }

        if (InputHandler.isTouched()){
            float tx = InputHandler.getX();
            float ty = InputHandler.getY();
            currentEnginePower+=100*delta;
            if (currentEnginePower>maxEnginePower)currentEnginePower = maxEnginePower;
            if (tx<300f&&ty<300f){
                v.add(-0.6f,0f);
            }else if (tx>300f&&ty<300f&&tx<800f) {
                v.add(0.6f, 0f);
            }

        }
        checkAndHandleBounds();

    }

    @Override
    public void death() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight()*2,pos);
        hp = 0;
    }

    private void checkAndHandleBounds(){
        if (getRight()<worldBounds.getLeft()+0.1f)setRight(worldBounds.getLeft()+0.1f);
        if (getLeft()>worldBounds.getRight()-0.1f)setLeft(worldBounds.getRight()-0.1f);
    }
    public boolean isArrowCollision(Rect arrow){
        return !(arrow.getRight()<pos.x
                ||arrow.getLeft()>pos.x
                ||arrow.getBottom()>pos.y
                ||arrow.getTop()<getBottom());
    }

}

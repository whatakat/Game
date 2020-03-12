package com.mygdx.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.pools.ExplosionPool;

public class Enemy extends Ship {

    private enum State{DESCENT,FIGHT}
    private State state;

    private Vector2 v0 = new Vector2();
    private Vector2 descentV = new Vector2(0,-0.5f);
    public Enemy(ArrowPool arrowPool, Rect worldBounds, ExplosionPool explosionPool, Sound sound) {
        super(arrowPool, worldBounds, explosionPool, sound);
        this.v.set(v0);
        this.state = State.DESCENT;
        this.v.set(descentV);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
        switch (state){
            case DESCENT:
            if(getTop() <= worldBounds.getTop()){
                v.set(v0);
                state=State.FIGHT;
            }
            break;
            case FIGHT:
                reloadTimer +=delta;
                if (reloadTimer>=reloadInterval){
                    reloadTimer = 0f;
                    shoot();
                }
                if (getTop()<worldBounds.getBottom()){
                    destroy();
                }
                break;
        }
    }
    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion arrowRegion,
            float arrowHeight,
            float arrowVY,
            int arrowDamage,
            float reloadInterval,
            float height,
            int hp
    ){
        this.regions = regions;
        this.v0.set(v0);
        this.arrowRegion = arrowRegion;
        this.arrowHeight  =arrowHeight;
        this.arrowV.set(0f,arrowVY);
        this.arrowDamage = arrowDamage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        reloadTimer = reloadInterval;
        this.v.set(descentV);
        this.state = State.DESCENT;
        this.hp = hp;


    }
}

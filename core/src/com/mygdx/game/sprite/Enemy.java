package com.mygdx.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.pools.ExplosionPool;

public class Enemy extends Ship {

    private Vector2 v0 = new Vector2();
    public Enemy(ArrowPool arrowPool, Rect worldBounds, ExplosionPool explosionPool, Sound sound) {
        super(arrowPool, worldBounds, explosionPool, sound);
        this.v.set(v0);
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
    }
    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion arrowRegion,
            float arrowHeight,
            float arrowVY,
            int arrowDamage,
            float reloadInterval,
            float height
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
        v.set(v0);


    }
}

package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;


public class Arrow extends Sprite {
    private Rect worldBounds;
    public   Vector2 v = new Vector2();
    private int damage;
    private Object owner;


    public Arrow() {
        regions = new TextureRegion[1];
    }
    public void set(
            Object owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 vo,
            float height,
            Rect worldBounds,
            int damage
    ){
        this.owner = owner;
        this.regions[0]=region;
        this.pos.set(pos0);
        this.v.set(vo);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v,delta);
        if (isOutside(worldBounds)){
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }
}

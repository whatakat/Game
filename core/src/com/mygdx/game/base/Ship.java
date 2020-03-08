package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.sprite.Arrow;

public class Ship extends Sprite {
    protected Vector2 v = new Vector2();
    protected Rect worldBounds;

    protected ArrowPool arrowPool;
    protected TextureRegion arrowRegion;

    protected final Vector2 arrowV = new Vector2();
    protected float arrowHeight;
    protected int arrowDamage;

    protected float reloadInterval;
    protected float reloadTimer;
    protected Music music;


    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }
    public Ship(ArrowPool arrowPool, Rect worldBounds) {
        this.arrowPool = arrowPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }
    public void shootSound(){
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/arrow.mp3"));
        music.play();
    }

    protected void shoot(){
        Arrow arrow = arrowPool.obtain();
        arrow.set(this,arrowRegion,pos, arrowV, arrowHeight,arrow,arrowDamage);
        shootSound();
    }
}

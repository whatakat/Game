package com.mygdx.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.pools.ExplosionPool;
import com.mygdx.game.sprite.Arrow;
import com.mygdx.game.sprite.Explosion;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;
    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    protected Vector2 v = new Vector2();
    protected Rect worldBounds;

    protected ArrowPool arrowPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion arrowRegion;

    protected final Vector2 arrowV = new Vector2();
    protected float arrowHeight;
    protected int arrowDamage;

    protected float reloadInterval;
    protected float reloadTimer;

    protected int hp;
    private Sound shootSound;


    public Ship(TextureRegion region, int rows, int cols, int frames, Sound sound,Rect worldBounds) {
        super(region, rows, cols, frames);
        this.shootSound = sound;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        damageAnimateTimer +=delta;
        if (damageAnimateTimer>=DAMAGE_ANIMATE_INTERVAL){
            frame = 0;
        }
    }

    public Ship(ArrowPool arrowPool, Rect worldBounds, ExplosionPool explosionPool, Sound sound) {
        this.arrowPool = arrowPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.shootSound = sound;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot(){
        Arrow arrow = arrowPool.obtain();
        arrow.set(this,arrowRegion,pos, arrowV, arrowHeight,arrow,arrowDamage);
        shootSound.play();
    }
    protected void death(){
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(),pos);
        hp = 0;
    }
    public void damage(int damage){
        frame = 1;
        damageAnimateTimer = 0f;
        hp -=damage;
        if (hp<=0){
            death();
            destroy();
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}

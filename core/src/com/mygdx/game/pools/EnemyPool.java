package com.mygdx.game.pools;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Enemy;
import com.mygdx.game.sprite.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {
    private ArrowPool arrowPool;
    private ExplosionPool explosionPool;
    private Rect worldBounds;
    private MainShip mainShip;
    private Sound sound;

    public EnemyPool (ArrowPool arrowPool, Rect worldBounds, ExplosionPool explosionPool, MainShip mainShip, Sound sound){
        this.arrowPool = arrowPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.mainShip = mainShip;
        this.sound = sound;
    }
    @Override
    protected Enemy newObject() {
        return new Enemy(arrowPool,worldBounds,explosionPool, mainShip, sound);
    }
}

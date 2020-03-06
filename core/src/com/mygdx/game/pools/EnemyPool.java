package com.mygdx.game.pools;

import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {
    private ArrowPool arrowPool;
    private Rect worldBoinds;
    public EnemyPool (ArrowPool arrowPool, Rect worldBounds){
        this.arrowPool = arrowPool;
        this.worldBoinds = worldBounds;
    }
    @Override
    protected Enemy newObject() {
        return new Enemy(arrowPool,worldBoinds);
    }
}
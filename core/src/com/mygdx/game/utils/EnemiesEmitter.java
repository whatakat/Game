package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;
import com.mygdx.game.pools.EnemyPool;
import com.mygdx.game.sprite.Enemy;

public class EnemiesEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.2f;
    private static final float ENEMY_SMALL_ARROW_HEIGHT = 0.4f;
    private static final float ENEMY_SMALL_ARROW_VY = -0.6f;
    private static final int ENEMY_SMALL_ARROW_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 14f;

    private Rect worldBounds;

    private float generateInterval = 10f;
    private float generateTimer;

    private TextureRegion[] enemySmallRegion;

    private Vector2 enemySmallV = new Vector2(0f,-0.15f);

    private TextureRegion arrowRegion;

    private EnemyPool enemyPool;

    public EnemiesEmitter(Rect worldBounds, EnemyPool enemyPool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;
        TextureRegion textureRegion0 = atlas.findRegion("sharkSmall");
        this.enemySmallRegion = Regions.split(textureRegion0,1,1,1);
        this.arrowRegion = atlas.findRegion("echo");
    }
    public void generateEnemies(float delta){
        generateTimer+=delta;
        if (generateTimer>=generateInterval){
            generateTimer  = 0f;
            Enemy enemy = enemyPool.obtain();
            enemy.set(
                    enemySmallRegion,
                    enemySmallV,
                    arrowRegion,
                    ENEMY_SMALL_ARROW_HEIGHT,
                    ENEMY_SMALL_ARROW_VY,
                    ENEMY_SMALL_ARROW_DAMAGE,
                    ENEMY_SMALL_RELOAD_INTERVAL,
                    ENEMY_SMALL_HEIGHT
            );
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft()+enemy.getHalfWidth(),worldBounds.getRight()-enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());

        }
    }
}

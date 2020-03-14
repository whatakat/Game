package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;
import com.mygdx.game.pools.EnemyPool;
import com.mygdx.game.sprite.Enemy;

public class EnemiesEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.14f;
    private static final float ENEMY_SMALL_ARROW_HEIGHT = 0.4f;
    private static final float ENEMY_SMALL_ARROW_VY = -0.6f;
    private static final int ENEMY_SMALL_ARROW_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 3;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.3f;
    private static final float ENEMY_MEDIUM_ARROW_HEIGHT = 0.4f;
    private static final float ENEMY_MEDIUM_ARROW_VY = -0.6f;
    private static final int ENEMY_MEDIUM_ARROW_DAMAGE = 1;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_MEDIUM_HP = 10;

    private static final float ENEMY_BIG_HEIGHT = 0.5f;
    private static final float ENEMY_BIG_ARROW_HEIGHT = 0.4f;
    private static final float ENEMY_BIG_ARROW_VY = -0.6f;
    private static final int ENEMY_BIG_ARROW_DAMAGE = 1;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_BIG_HP = 20;


    private Rect worldBounds;

    private float generateInterval = 10f;
    private float generateTimer;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyMediumRegion;
    private TextureRegion[] enemyBigRegion;

    private Vector2 enemySmallV = new Vector2(0f,-0.10f);
    private Vector2 enemyMediumV = new Vector2(0f,-0.05f);
    private Vector2 enemyBigV = new Vector2(0f,-0.04f);

    private TextureRegion arrowRegion;

    private EnemyPool enemyPool;

    public EnemiesEmitter(Rect worldBounds, EnemyPool enemyPool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;
        TextureRegion textureRegion0 = atlas.findRegion("sharkSmall");
        this.enemySmallRegion = Regions.split(textureRegion0,1,2,2);
        TextureRegion textureRegion1 = atlas.findRegion("sharkMedium");
        this.enemyMediumRegion = Regions.split(textureRegion1,1,2,2);
        TextureRegion textureRegion2 = atlas.findRegion("sharkMedium");
        this.enemyBigRegion = Regions.split(textureRegion2,1,2,2);
        this.arrowRegion = atlas.findRegion("echo");
    }
    public void generateEnemies(float delta){
        generateTimer+=delta;
        if (generateTimer>=generateInterval){
            generateTimer  = 0f;
            float type = (float)Math.random();
            Enemy enemy = enemyPool.obtain();
            if (type<0.7f) {
                enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        arrowRegion,
                        ENEMY_SMALL_ARROW_HEIGHT,
                        ENEMY_SMALL_ARROW_VY,
                        ENEMY_SMALL_ARROW_DAMAGE,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP
                );
            }else if (type<0.9f){
                enemy.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        arrowRegion,
                        ENEMY_MEDIUM_ARROW_HEIGHT,
                        ENEMY_MEDIUM_ARROW_VY,
                        ENEMY_MEDIUM_ARROW_DAMAGE,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP
                );
            }else {
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        arrowRegion,
                        ENEMY_BIG_ARROW_HEIGHT,
                        ENEMY_BIG_ARROW_VY,
                        ENEMY_BIG_ARROW_DAMAGE,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP
                );

            }
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft()+enemy.getHalfWidth(),worldBounds.getRight()-enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());

        }
    }
}

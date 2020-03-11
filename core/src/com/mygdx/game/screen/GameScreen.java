package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.pools.EnemyPool;
import com.mygdx.game.pools.ExplosionPool;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.Explosion;
import com.mygdx.game.sprite.MainShip;
import com.mygdx.game.sprite.Wave;
import com.mygdx.game.sprite.WaveBg;
import com.mygdx.game.utils.EnemiesEmitter;

public class GameScreen extends BaseScreen {
    private static final int WAVE_COUNT = 250;
    private static final int WAVEBG_COUNT = 3;
    private static final float WAVE_HEIGHT = 0.3f;
    private float SPEED_WAVE = 0.03f;

    private Background background;
    private Texture bg;
    private TextureAtlas atlas;
    private Wave[] wave;
    private MainShip mainShip;
    private WaveBg[] waveBg;
    private ArrowPool arrowPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;
    private EnemiesEmitter enemiesEmitter;
    private Music music;
    private Sound explosionSound;
    private Sound arrowSound;
    private Sound enemyBulletSound;


    public GameScreen(Game game) {
        super(game);
    }



    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background_okean.jpg");
        background = new Background(new TextureRegion(bg));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/myBoat.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/arrowSh.wav"));
        arrowSound = Gdx.audio.newSound(Gdx.files.internal("sounds/arrow.wav"));
        enemyBulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/SonarPing.wav"));
        music.setLooping(true);
        music.play();

        atlas = new TextureAtlas("textures/atlas.pack");
        TextureRegion waveBgRegion = atlas.findRegion("background_ocean");
        waveBg = new WaveBg[WAVEBG_COUNT];
        for (int i = 0; i <waveBg.length ; i++) {
            waveBg[i] = new WaveBg(waveBgRegion,0f,SPEED_WAVE,1f);
        }
        arrowPool = new ArrowPool();
        explosionPool = new ExplosionPool(atlas,explosionSound);
        enemyPool = new EnemyPool(arrowPool,worldBounds,explosionPool,enemyBulletSound);
        enemiesEmitter = new EnemiesEmitter(worldBounds,enemyPool,atlas);
        TextureRegion waveRegion = atlas.findRegion("eWave");
        wave = new Wave[WAVE_COUNT];
        for (int i = 0; i <wave.length ; i++) {
            // wave[i] = new Wave(waveRegion, Rnd.nextFloat(0.005f,-0.005f),Rnd.nextFloat(0.1f,-0.1f),0.1f);
            wave[i] = new Wave(waveRegion, 0f,0.07f,WAVE_HEIGHT);
        }
        mainShip = new MainShip(atlas,arrowPool,explosionPool,arrowSound);


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (WaveBg w:waveBg){
            w.draw(batch);
        }
        arrowPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        for (Wave w: wave){
            w.draw(batch);
        }
        mainShip.draw(batch);
        batch.end();
    }
    public void update(float delta){
        for (WaveBg w: waveBg){
            w.update(delta);
        }
        arrowPool.updateActiveSprites(delta);
        enemyPool.updateActiveSprites(delta);
        explosionPool.updateActiveSprites(delta);
        enemiesEmitter.generateEnemies(delta);
        for (Wave w: wave){
            w.update(delta);
        }
        mainShip.update(delta);

    }

    public void checkCollisions(){

    }
    public void deleteAllDestroyed(){
        arrowPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i <waveBg.length ; i++) {
            if (i==1){
                waveBg[i].resize(worldBounds,0.4f);
            }else if (i==2){
                waveBg[i].resize(worldBounds,1.2f);
            }else waveBg[i].resize(worldBounds);

        }
        for (Wave w: wave){
            w.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        arrowPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        music.dispose();
        explosionSound.dispose();
        arrowSound.dispose();
        enemyBulletSound.dispose();
        super.dispose();
    }

//    @Override
//    public void touchDown(Vector2 touch, int pointer) {
//        mainShip.touchDown(touch, pointer);
//        Explosion explosion = explosionPool.obtain();
//        explosion.set(0.3f, touch);
//    }
}

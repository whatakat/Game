package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.MainShip;
import com.mygdx.game.sprite.Wave;
import com.mygdx.game.sprite.WaveBg;

public class GameScreen extends BaseScreen {
    private static final int WAVE_COUNT = 250;
    private static final int WAVEBG_COUNT = 3;
    private static final float WAVE_HEIGHT = 0.25f;
    private float SPEED_WAVE = 0.03f;

    private Background background;
    private Texture bg;
    private TextureAtlas atlas;
    private TextureAtlas atlasShip;
    private Wave[] wave;
    private MainShip mainShip;
    private WaveBg[] waveBg;
    private ArrowPool arrowPool;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background_okean.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/atlas.pack");
        TextureRegion waveBgRegion = atlas.findRegion("background_okean");
        waveBg = new WaveBg[WAVEBG_COUNT];
        for (int i = 0; i <waveBg.length ; i++) {
            waveBg[i] = new WaveBg(waveBgRegion,0f,SPEED_WAVE,1f);
        }
        atlasShip = new TextureAtlas("textures/ship.pack");
        TextureRegion waveRegion = atlas.findRegion("eWave3");
        wave = new Wave[WAVE_COUNT];
        for (int i = 0; i <wave.length ; i++) {
            // wave[i] = new Wave(waveRegion, Rnd.nextFloat(0.005f,-0.005f),Rnd.nextFloat(0.1f,-0.1f),0.1f);
            wave[i] = new Wave(waveRegion, 0.004f,0.06f,WAVE_HEIGHT);
        }
        arrowPool = new ArrowPool();
        mainShip = new MainShip(atlasShip,arrowPool);
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
        for (Wave w: wave){
            w.draw(batch);
        }
        mainShip.draw(batch);
        arrowPool.drawActiveSprites(batch);
        batch.end();
    }
    public void update(float delta){
        for (WaveBg w: waveBg){
            w.update(delta);
        }
        for (Wave w: wave){
            w.update(delta);
        }
        mainShip.update(delta);
        arrowPool.updateActiveSprites(delta);

    }

    public void checkCollisions(){

    }
    public void deleteAllDestroyed(){
        arrowPool.freeAllDestroyedActiveSprites();

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i <waveBg.length ; i++) {
            if (i==1){
                waveBg[i].resize(worldBounds,1f);
            }else
            waveBg[i].resize(worldBounds);
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
        atlasShip.dispose();
        arrowPool.dispose();
        super.dispose();
    }
}

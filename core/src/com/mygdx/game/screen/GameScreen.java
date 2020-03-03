package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.MainShip;
import com.mygdx.game.sprite.Wave;

public class GameScreen extends BaseScreen {
    private static final int WAVE_COUNT = 125;
    private static final float WAVE_HEIGHT = 0.26f;

    private Background background;
    private Texture bg;
    private TextureAtlas atlas;
    private TextureAtlas atlasShip;
    private Wave wave[];
    private MainShip mainShip;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background_okean.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/atlas.pack");
        atlasShip = new TextureAtlas("textures/ship.pack");
        TextureRegion waveRegion = atlas.findRegion("eWave4");
        wave = new Wave[WAVE_COUNT];
        for (int i = 0; i <wave.length ; i++) {
            // wave[i] = new Wave(waveRegion, Rnd.nextFloat(0.005f,-0.005f),Rnd.nextFloat(0.1f,-0.1f),0.1f);
            wave[i] = new Wave(waveRegion, 0.004f,0.04f,WAVE_HEIGHT);
        }
        mainShip = new MainShip(atlasShip);
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
        for (int i = 0; i <wave.length ; i++) {
            wave[i].draw(batch);
        }
        mainShip.draw(batch);
        batch.end();
    }
    public void update(float delta){
        for (int i = 0; i <wave.length ; i++) {
            wave[i].update(delta);
        }
        mainShip.update(delta);

    }

    public void checkCollisions(){

    }
    public void deleteAllDestroyed(){

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i <wave.length ; i++) {
            wave[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        atlasShip.dispose();
        super.dispose();
    }
}

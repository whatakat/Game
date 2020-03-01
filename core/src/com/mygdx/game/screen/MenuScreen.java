package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.Wave;

public class MenuScreen extends BaseScreen {
    private static final int WAVE_COUNT = 250;
    private static final float WAVE_HEIGHT = 0.13f;
   private Background background;
   private Texture bg;
   private TextureAtlas atlas;
   private Wave wave[];


    public MenuScreen(Game game){
        super(game);
    }


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background_okean.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/atlas.pack");
        TextureRegion waveRegion = atlas.findRegion("eWave4");
        wave = new Wave[WAVE_COUNT];
        for (int i = 0; i <wave.length ; i++) {
           // wave[i] = new Wave(waveRegion, Rnd.nextFloat(0.005f,-0.005f),Rnd.nextFloat(0.1f,-0.1f),0.1f);
            wave[i] = new Wave(waveRegion, 0.004f,0.04f,WAVE_HEIGHT);
        }



    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i <wave.length ; i++) {
            wave[i].draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i <wave.length ; i++) {
            wave[i].resize(worldBounds);
        }

    }
    public void update(float delta){
        for (int i = 0; i <wave.length ; i++) {
            wave[i].update(delta);
        }
    }
}

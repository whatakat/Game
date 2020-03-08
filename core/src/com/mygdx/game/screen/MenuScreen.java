package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.ButtonExit;
import com.mygdx.game.sprite.ButtonPlay;
import com.mygdx.game.sprite.Wave;

public class MenuScreen extends BaseScreen implements ActionListener {
    private static final int WAVE_COUNT = 0;
    private static final float WAVE_HEIGHT = 0.13f;

    private static final float PRESS_SCALE = 0.9f;
    private static final float BUTTON_HEIGHT = 0.15f;

   private Background background;
   private Texture bg;
   private TextureAtlas atlas;
   private Wave wave[];

   private ButtonExit buttonExit;
   private ButtonPlay buttonPlay;
   private Music music;



    public MenuScreen(Game game){
        super(game);
    }


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/port.jpg");
        background = new Background(new TextureRegion(bg));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundSound.mp3"));
        music.setLooping(false);
        music.play();
        atlas = new TextureAtlas("textures/atlas.pack");
        TextureRegion waveRegion = atlas.findRegion("eWave4");
        wave = new Wave[WAVE_COUNT];
        for (int i = 0; i <wave.length ; i++) {
           // wave[i] = new Wave(waveRegion, Rnd.nextFloat(0.005f,-0.005f),Rnd.nextFloat(0.1f,-0.1f),0.1f);
            wave[i] = new Wave(waveRegion, 0.004f,0.04f,WAVE_HEIGHT);
        }
        buttonExit = new ButtonExit(atlas,this,PRESS_SCALE);
        buttonExit.setHeightProportion(BUTTON_HEIGHT);
        buttonPlay = new ButtonPlay(atlas,this,PRESS_SCALE);
        buttonPlay.setHeightProportion(BUTTON_HEIGHT);



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
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
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
        buttonExit.touchDown(touch,pointer);
        buttonPlay.touchDown(touch,pointer);
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {

        super.touchUp(touch, pointer);
        buttonExit.touchUp(touch,pointer);
        buttonPlay.touchUp(touch,pointer);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i <wave.length ; i++) {
            wave[i].resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);

    }
    public void update(float delta){
        for (int i = 0; i <wave.length ; i++) {
            wave[i].update(delta);
        }
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == buttonExit){
            Gdx.app.exit();
        }else if (src == buttonPlay){
            game.setScreen(new GameScreen(game));

        }
        else {
            throw new RuntimeException("Unknown rsc");
        }

    }
}

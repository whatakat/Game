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
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.base.Font;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pools.ArrowPool;
import com.mygdx.game.pools.EnemyPool;
import com.mygdx.game.pools.ExplosionPool;
import com.mygdx.game.sprite.Arrow;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.ButtonNewGame;
import com.mygdx.game.sprite.Enemy;
import com.mygdx.game.sprite.MainShip;
import com.mygdx.game.sprite.MassageGameOver;
import com.mygdx.game.sprite.Wave;
import com.mygdx.game.sprite.WaveBg;
import com.mygdx.game.utils.EnemiesEmitter;

import java.util.List;

public class GameScreen extends BaseScreen implements ActionListener {
    private static final int WAVE_COUNT = 300;
    private static final int WAVEBG_COUNT = 3;
    private static final float WAVE_HEIGHT = 0.25f;
    private float SPEED_WAVE = 0.03f;
    private static final float FONT_SIZE = 0.02f;

    private enum State{PLAYING, GAME_OVER}
    private State state;

    private Background background;
    private Texture bg;
    private TextureAtlas atlas;
    private Wave[] wave;
    private MainShip mainShip;
    private WaveBg[] waveBg;
    private ArrowPool arrowPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPoolShark;
    private ExplosionPool explosionPoolShip;
    private EnemiesEmitter enemiesEmitter;
    private Music music;
    private Sound explosionSound;
    private Sound arrowSound;
    private Sound hitEnemy;
    private Music sharkUnderBoat;

    private MassageGameOver massageGameOver;
    private ButtonNewGame buttonNewGame;

    private Font font;
    private StringBuilder sbCt = new StringBuilder();
    private StringBuilder sbHp = new StringBuilder();
    private StringBuilder sbStage = new StringBuilder();

    private int countDeath;


    protected GameScreen(Game game) {
        super(game);
    }



    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background_okean.jpg");
        background = new Background(new TextureRegion(bg));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/myBoat.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/expWater.wav"));
        arrowSound = Gdx.audio.newSound(Gdx.files.internal("sounds/arrow.wav"));
        hitEnemy = Gdx.audio.newSound(Gdx.files.internal("sounds/hitShark.wav"));
        sharkUnderBoat = Gdx.audio.newMusic(Gdx.files.internal("sounds/sharkEnemy.mp3"));
        music.setLooping(true);
        music.play();

        atlas = new TextureAtlas("textures/atlas.pack");
        TextureRegion waveBgRegion = atlas.findRegion("background_ocean");
        waveBg = new WaveBg[WAVEBG_COUNT];
        for (int i = 0; i <waveBg.length ; i++) {
            waveBg[i] = new WaveBg(waveBgRegion,0f,SPEED_WAVE,1f);
        }
        arrowPool = new ArrowPool();
        explosionPoolShark = new ExplosionPool(atlas,explosionSound,"overG");
        explosionPoolShip = new ExplosionPool(atlas,explosionSound,"overS");
        mainShip = new MainShip(atlas,worldBounds,arrowPool,explosionPoolShip,arrowSound);
        enemyPool = new EnemyPool(arrowPool,worldBounds,explosionPoolShark, mainShip, hitEnemy);
        enemiesEmitter = new EnemiesEmitter(worldBounds,enemyPool,atlas);
        TextureRegion waveRegion = atlas.findRegion("eWave");
        wave = new Wave[WAVE_COUNT];
        for (int i = 0; i <wave.length ; i++) {
            wave[i] = new Wave(waveRegion, 0f,0.07f,WAVE_HEIGHT);
        }
        massageGameOver = new MassageGameOver(atlas);
        buttonNewGame = new ButtonNewGame(atlas,this);
        font = new Font("font/font.fnt","font/font.png");
        font.setWorldSize(FONT_SIZE);
        startNewGame();
    }
    private void printInfo(){
        sbCt.setLength(0);
        sbHp.setLength(0);
        sbStage.setLength(0);
        font.draw(batch,sbCt.append("Count shark:").append(countDeath),worldBounds.getTop(),worldBounds.getTop());
        font.draw(batch,sbHp.append(">").append(mainShip.getHp()),mainShip.pos.x+mainShip.getWidth()/(float)4,mainShip.pos.y);
        font.draw(batch,sbStage.append("Stage:").append(enemiesEmitter.getStage()),worldBounds.getLeft(),worldBounds.getTop());

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
        explosionPoolShark.drawActiveSprites(batch);
        explosionPoolShip.drawActiveSprites(batch);
        for (Wave w: wave){
            w.draw(batch);
        }
        mainShip.draw(batch);
        if (state==State.GAME_OVER){
            massageGameOver.draw(batch);
            buttonNewGame.draw(batch);
        }
        printInfo();
        batch.end();
    }
    public void update(float delta){
        explosionPoolShark.updateActiveSprites(delta);
        explosionPoolShip.updateActiveSprites(delta);

        switch (state){
            case PLAYING:
                for (WaveBg w: waveBg){
                    w.update(delta);
                }
                for (Wave w: wave){
                    w.update(delta);
                }
                music.play();
                arrowPool.updateActiveSprites(delta);
                enemyPool.updateActiveSprites(delta);
                enemiesEmitter.generateEnemies(delta,countDeath);
                mainShip.update(delta);
                if (mainShip.isDestroyed()){
                    state = State.GAME_OVER;
                }
                break;
            case GAME_OVER:
                music.pause();
                break;
        }

    }

    private void checkCollisions(){
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        for (Enemy enemy: enemyList){
            if (enemy.isDestroyed()){
                continue;
            }
            float minDist = enemy.getHalfWidth()+mainShip.getHalfWidth();
            if (enemy.pos.dst2(mainShip.pos)< minDist*minDist/(float)3){
                enemy.destroy();
                mainShip.setHp(mainShip.getHp()-2);
                sharkUnderBoat.play();
                continue;
            }
            if (enemy.pos.dst2(mainShip.pos)< minDist*minDist&&mainShip.getHp()<3){
                enemy.destroy();
                mainShip.death();
                mainShip.destroy();
                state = State.GAME_OVER;
                return;
            }
        }
        List<Arrow> arrowList = arrowPool.getActiveObjects();
        for (Arrow arrow: arrowList){
            if (arrow.isDestroyed() || arrow.getOwner() == mainShip){
                continue;
            }
            if (mainShip.isArrowCollision(arrow)){
                mainShip.damage(arrow.getDamage());
                arrow.destroy();
            }
        }
        for (Enemy enemy: enemyList){
            if (enemy.isDestroyed()){
                continue;
            }
            for (Arrow arrow: arrowList){
                if (arrow.getOwner() != mainShip || arrow.isDestroyed()){
                    continue;
                }
                if (enemy.isArrowCollision(arrow)){
                    enemy.damage(arrow.getDamage());
                    arrow.destroy();
                    enemy.setVelocity(0f,-0.03f);//I have change
                    if (enemy.isDestroyed()){
                        countDeath++;
                        break;
                    }
                }
            }

        }

    }
    private void deleteAllDestroyed(){
        arrowPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
        explosionPoolShark.freeAllDestroyedActiveSprites();
        explosionPoolShip.freeAllDestroyedActiveSprites();


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
        explosionPoolShark.dispose();
        explosionPoolShip.dispose();
        music.dispose();
        hitEnemy.dispose();
        explosionSound.dispose();
        arrowSound.dispose();
        sharkUnderBoat.dispose();
        font.dispose();
        super.dispose();
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        buttonNewGame.touchDown(touch, pointer);
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        buttonNewGame.touchUp(touch, pointer);
    }

    private void startNewGame(){
        state = State.PLAYING;
        countDeath = 0;
        mainShip.setNewGame();
        enemiesEmitter.setToNewGame();
        arrowPool.freeAllActiveSprites();
        enemyPool.freeAllActiveSprites();
        explosionPoolShark.freeAllActiveSprites();
        explosionPoolShip.freeAllActiveSprites();


    }
    @Override
    public void actionPerformed(Object src) {
        if (src == buttonNewGame){
            startNewGame();
        }

    }
}

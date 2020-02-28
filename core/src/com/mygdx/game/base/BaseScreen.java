package com.mygdx.game.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.MatrixUtils;
import com.mygdx.game.math.Rect;

public class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    protected Game game;
    private Rect screenBounds;
    private Rect worldBounds;
    private Rect glBounds;

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;

    private Vector2 touch = new Vector2();

    public BaseScreen(Game game){
        this.game = game;
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0,0,1f,1f);
        this.worldToGl = new Matrix4();
        this.screenToWorld = new Matrix3();
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        screenBounds.setSize(width,height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = width/(float)height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f*aspect);
        MatrixUtils.calcTransitionMatrix(worldToGl,worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        MatrixUtils.calcTransitionMatrix(screenToWorld,screenBounds,worldBounds);
        resize(worldBounds);

    }
    public void resize(Rect worldBounds){
        System.out.println("resize w="+worldBounds.getWidth()+" h="+worldBounds.getHeight());

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        batch.dispose();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX,screenBounds.getHeight()-screenY).mul(screenToWorld);
        touchDown(touch,pointer);

        return false;
    }
    public void touchDown(Vector2 touch, int pointer){

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX,screenBounds.getHeight()-screenY).mul(screenToWorld);
        touchUp(touch,pointer);
        return false;
    }
    public void touchUp(Vector2 touch, int pointer){

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX,screenBounds.getHeight()-screenY).mul(screenToWorld);
        touchDragged(touch,pointer);
        return false;
    }
    public void touchDragged(Vector2 touch, int pointer){

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

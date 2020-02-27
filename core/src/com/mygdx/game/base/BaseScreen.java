package com.mygdx.game.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class BaseScreen implements Screen {

    protected Game game;
    public BaseScreen(Game game){
        this.game = game;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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

    }
}

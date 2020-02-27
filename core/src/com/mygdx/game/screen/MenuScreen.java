package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    SpriteBatch batch;
    public MenuScreen(Game game){
        super(game);
    }


    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}

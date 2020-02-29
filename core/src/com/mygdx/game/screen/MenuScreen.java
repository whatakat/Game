package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.Wave;

public class MenuScreen extends BaseScreen {
   private Background background;
   private Texture bg;
   private Wave wave;
   private TextureAtlas atlas;


    public MenuScreen(Game game){
        super(game);
    }


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background_okean.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/sharkg.pack");
        TextureRegion waveRegion = atlas.findRegion("eW");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
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
    }
}

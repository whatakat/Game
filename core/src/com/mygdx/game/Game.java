package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Hero hero;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero = new Hero();
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		hero.render(batch);
		//SharkEmitter.getInstance().render(batch);
		//ArrowEmitter.getInstance().render(batch);
		batch.end();
			}
			public void update(float dt){
		background.update(hero,dt);
		hero.update(dt);
				//SharkEmitter.getInstance().update(dt);
				//ArrowEmitter.getInstance().update(dt);


			}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}

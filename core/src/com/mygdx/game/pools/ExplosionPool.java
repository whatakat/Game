package com.mygdx.game.pools;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureRegion textureRegion;

    public ExplosionPool(TextureAtlas atlas) {
        textureRegion = atlas.findRegion("gShark");
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(textureRegion,3,3,9);
    }
}

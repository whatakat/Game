package com.mygdx.game.pools;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {


    private Sound sound;
    private TextureRegion textureRegion;

    public ExplosionPool(TextureAtlas atlas, Sound sound, String region) {
        this.textureRegion = atlas.findRegion(region);
        this.sound = sound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(textureRegion,9,9,81, sound);
    }
}

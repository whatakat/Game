package com.mygdx.game.sprite;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;

public class Explosion extends Sprite {
    private Sound sound;
    private float animateInterval = 0.01f;
    private float animateTimer;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound sound){
        super(region, rows, cols, frames);
        this.sound = sound;
    }
    public void set(float height, Vector2 pos){
        this.pos.set(pos);
        setHeightProportion(height);
        sound.play();
    }

    @Override
    public void update(float delta) {
        animateTimer+=delta;
        if (animateTimer>=animateInterval){
            animateTimer = 0f;
            if (++frame==regions.length){
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }
}

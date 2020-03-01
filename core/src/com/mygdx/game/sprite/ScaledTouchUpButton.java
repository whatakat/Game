package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;

public class ScaledTouchUpButton extends Sprite {

    private int pointer;
    private boolean pressed;
    private float pressScale;


    public ScaledTouchUpButton(TextureRegion region, float pressScale) {
        super(region);
        this.pressScale = pressScale;
        this.pressed = false;
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        if (pressed||!isMe(touch)){
            return;
        }
        this.pointer = pointer;
        scale = pressScale;
        this.pressed = true;
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer||!pressed){
            return;
        }
        if (isMe(touch)){
            // do something
            return;
        }
        this.pressed = false;
        this.scale = 1f;
    }
}

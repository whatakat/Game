package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.ScaledTouchUpButton;
import com.mygdx.game.math.Rect;

public class ButtonPlay extends ScaledTouchUpButton {

    public ButtonPlay(TextureAtlas atlas, ActionListener actionListener, float pressScale) {
        super(atlas.findRegion("play"),actionListener,pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}

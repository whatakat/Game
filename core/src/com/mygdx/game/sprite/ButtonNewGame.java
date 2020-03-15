package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.ScaledTouchUpButton;

public class ButtonNewGame extends ScaledTouchUpButton {

    private static final float HEIGHT = 0.1f;
    private static final float TOP  = -0.012f;
    private static final float PRESS_SCALE = 0.9f;
    public ButtonNewGame(TextureRegion region, ActionListener actionListener) {
        super(region, actionListener, PRESS_SCALE);
        setHeightProportion(HEIGHT);
        setTop(TOP);
    }
}

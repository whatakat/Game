package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.Sprite;

public class MassageGameOver extends Sprite {

    private static final float HEIGHT = 0.1f;
    private static final float BOTTOM_MARGIN = 0.01f;

    public MassageGameOver(TextureAtlas atlas){
        super(atlas.findRegion("gameOver"));
        setHeightProportion(HEIGHT);
        setBottom(BOTTOM_MARGIN);

    }
}

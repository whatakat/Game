package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.Sprite;

public class MainShip extends Sprite {

    private static final float SHIP_HEIGHT = 0.15f;

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("Hero"));
    }
}

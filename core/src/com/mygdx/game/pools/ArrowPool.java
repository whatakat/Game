package com.mygdx.game.pools;

import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.sprite.Arrow;

public class ArrowPool extends SpritesPool<Arrow> {
    @Override
    protected Arrow newObject() {
        return new Arrow();
    }
}

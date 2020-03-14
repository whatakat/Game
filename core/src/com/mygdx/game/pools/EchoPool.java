package com.mygdx.game.pools;

import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.sprite.Arrow;
import com.mygdx.game.sprite.Echo;

public class EchoPool extends SpritesPool<Echo> {
    @Override
    protected Echo newObject() {
        return new Echo();
    }
}

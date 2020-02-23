package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Background {
    class Dolphin{
        Vector2 position;
        Vector2 velocity;
        float scl;
        public Dolphin(){
            position = new Vector2((float)Math.random()*1280,(float)Math.random()*720);
            velocity = new Vector2((float)(Math.random()-0.5)*5f,(float)(Math.random()-0.5)*5f);
            scl = 0.5f+(float)Math.random()/4.0f;
        }
    }
}

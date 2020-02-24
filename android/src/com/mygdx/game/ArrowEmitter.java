package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class ArrowEmitter {
    private static final ArrowEmitter ourInstance = new ArrowEmitter();
    public static ArrowEmitter getInstance(){
        return ourInstance;
    }
    Texture texture;
    Arrow[] arrows;

    private ArrowEmitter(){
        texture = new Texture("");
        arrows = new Arrow[100];
        for (int i = 0; i <arrows.length ; i++) {
            arrows[i] = new Arrow();
        }
    }


}

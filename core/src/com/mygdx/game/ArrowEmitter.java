package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArrowEmitter {
    private static final ArrowEmitter ourInstance = new ArrowEmitter();
    public static ArrowEmitter getInstance(){
        return ourInstance;
    }
    Texture texture;
    Arrow[] arrows;

    private ArrowEmitter(){
        texture = new Texture("gArrow.png");
        arrows = new Arrow[100];
        for (int i = 0; i <arrows.length ; i++) {
            arrows[i] = new Arrow();
        }
    }
    public void update(float dt){
        for (Arrow a: arrows){
            if (a.active){
                a.update(dt);
            }
        }
    }
    public void render(SpriteBatch batch){
        for (Arrow a: arrows){
            if (a.active){
                batch.draw(texture,a.position.x-16,a.position.y-16);
            }
        }
    }


}

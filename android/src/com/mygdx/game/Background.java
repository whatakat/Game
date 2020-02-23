package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        public void update(Hero hero, float dt){
            position.mulAdd(velocity, dt);
            position.mulAdd(hero.velocity,-0.001f);
            float half = textureDolphin.getWidth()*scl;
            if (position.x<-half)position.x = 1280+half;
            if (position.x>1280+half)position.x = -half;
            if (position.y<-half)position.y = 720+half;
            if (position.y>720+half)position.y = -half;
        }
    }
    Texture texture;
    Texture textureDolphin;
    Dolphin[] dolphins;
    public Background(){
        texture = new Texture();
        textureDolphin = new Texture();
        dolphins  = new Dolphin[30];
        for (int i = 0; i <dolphins.length ; i++) {
            dolphins[i] = new Dolphin();
        }
    }
    public void render(SpriteBatch batch){
        batch.draw(texture,0,0);
        for (Dolphin d: dolphins){
            batch.draw(textureDolphin, d.position.x-8, d.position.y-8,8,8,16,16,
            d.scl,d.scl,0,0,0,16,16,false,false);
        }
    }
    public void update(Hero hero, float dt){
        for (Dolphin d: dolphins){
            d.update(hero,dt);
        }
    }
    public void dispose(){
        texture.dispose();
        textureDolphin.dispose();
    }
}

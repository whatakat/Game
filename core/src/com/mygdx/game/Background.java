package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
//    class Wave{
//        Vector2 position;
//        Vector2 velocity;
//        float scl;
//        public Wave(){
//            position = new Vector2((float)Math.random()*2080,(float)Math.random()*1080);
//            velocity = new Vector2((float)(Math.random()-0.5)*25f,(float)(Math.random()-0.5)*25f);
//            scl = 0.5f+(float)Math.random()/2.0f;
//        }
//        public void update(Hero hero, float dt){
//            position.mulAdd(velocity, dt);
//            position.mulAdd(hero.velocity,-0.009f);
//            float half = textureWave.getWidth()*scl;
//            if (position.x<-half)position.x = 2080+half;
//            if (position.x>2080+half)position.x = -half;
//            if (position.y<-half)position.y = 1080+half;
//            if (position.y>1080+half)position.y = -half;
//        }
//    }
//    Texture texture;
//    Texture textureWave;
//    Wave[] waves;
//    public Background(){
//        texture = new Texture("background_okean.jpg");
//        textureWave = new Texture("eWave.png");
//        waves  = new Wave[100];
//        for (int i = 0; i <waves.length ; i++) {
//            waves[i] = new Wave();
//        }
//    }
//    public void render(SpriteBatch batch){
//        batch.draw(texture,0,0);
//        for (Wave w: waves){
//            batch.draw(textureWave, w.position.x-8, w.position.y-8,8,8,200,200,
//            w.scl,w.scl,0,0,0,200,200,false,false);
//        }
//    }
//    public void update(Hero hero, float dt){
//        for (Wave d: waves){
//            d.update(hero,dt);
//        }
//    }
//    public void dispose(){
//        texture.dispose();
//        textureWave.dispose();
//    }
}

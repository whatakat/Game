package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Shark {
    static Texture texture;
    Vector2 position;
    Vector2 velocity;
    float scl;
    float angle;
    int hp;
    int hpMax;
    Circle hitArea;

    public Shark(Vector2 position, Vector2 velocity, float scl,int hpMax){
        if (texture == null){
            texture = new Texture("gShark.png");
        }
        this.position = position;
        this.velocity = velocity;
        this.scl = scl;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.angle = 0.0f;
        this.hitArea = new Circle(position.x,position.y, 25);
    }
    public void render(SpriteBatch batch){
        batch.draw(texture,position.x -128,position.y -128,128,128,256,256,
                scl,scl,angle,0,0,256,256,false,false);
    }
    public boolean takeDamage(int dmg){
        hp -=dmg;
        if (hp<=0){
            return true;
        }
        return false;
    }
    public void update(float dt){
        position.mulAdd(velocity,dt);
        if (position.x<-128*scl)position.x = 2080+128*scl;
        if (position.x>2080+128*scl)position.x =-128*scl;
        if (position.y<-128*scl)position.y = 1080+128*scl;
        if (position.y>1080+128*scl)position.y =-128*scl;
        hitArea.x = position.x+25;
        hitArea.y = position.y;

        }
    }


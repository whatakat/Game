package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.floor;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;

public class Hero {
    Texture texture;
    Vector2 position;
    Vector2 velocity;
    float angle;

    int hp;
    int hpMax;

    float lowEnginePower;
    float currentEnginePower;
    float maxEnginePower;

    float rotationSpeed;

    float fireRate;
    float fireCounter;

    Circle hitArea;

    public Hero(){
        texture = new Texture("");
        position = new Vector2(640, 360);
        velocity = new Vector2(0,0);
        maxEnginePower = 400.0f;
        lowEnginePower = 200.0f;
        rotationSpeed = 3.14f;
        hpMax=100;
        hp = hpMax;
        hitArea = new Circle(position.x,position.y, 25);
        fireCounter = 0;
        fireRate = 0.25f;
    }
    public void render(SpriteBatch batch){
        batch.draw(texture,position.x - 32,position.y - 32,32,32,64,64,1,1,
                (float)toDegrees(angle),0,0,64,64,false,false);
    }
    public void update(float dt){
        position.mulAdd(velocity,dt);
        velocity.scl(0.97f);
        if (InputHandler.isJustTouched()){
            currentEnginePower = lowEnginePower;
        }
        if (InputHandler.isTouched()){
            float tx = InputHandler.getX();
            float ty = InputHandler.getY();
            float ang = (float)atan2(ty- position.y, tx - position.x);
            if (angle> ang){
                if (angle-ang < PI){
                    angle -=rotationSpeed*dt;
                }else {
                    angle +=rotationSpeed*dt;
                }
            }
            if (angle<ang){
                if (ang-angle<PI){
                    angle+=rotationSpeed*dt;
                }else {
                    angle-=rotationSpeed*dt;
                }
            }
            currentEnginePower+=100*dt;
            if (currentEnginePower>maxEnginePower)currentEnginePower = maxEnginePower;
            velocity.add((float)(currentEnginePower*cos(angle)*dt),(float)(currentEnginePower*
                    sin(angle)*dt));
        }
    }
    public void fire(){
        
    }

}

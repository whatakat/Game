package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.utils.Regions;

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;
    private boolean isDestroyed;

    public Sprite(){
    }

    public Sprite(TextureRegion region){
        if (region == null){
            throw  new NullPointerException();
        }
        regions = new TextureRegion[1];
        regions[0]=region;
    }
    public Sprite(TextureRegion region, int rows, int cols, int frames){
        if (region == null){
            throw  new NullPointerException();
        }
        regions = Regions.split(region,rows,cols,frames);
    }
    public void draw(SpriteBatch batch){
        if (isDestroyed){
            return;
        }
        batch.draw(regions[frame],getLeft(),getBottom(),halfWidth,halfHeight,getWidth(),getHeight(),
                scale,scale,angle);
    }
    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth()/(float)regions[frame].getRegionHeight();
        setWidth(height*aspect);
    }
    public void resize(Rect worldBounds){

    }
    public void touchDown(Vector2 touch, int pointer) {

    }

    public void touchUp(Vector2 touch, int pointer) {

    }
    public void update(float delta){

    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void destroy(){
        this.isDestroyed = true;
    }
    public  void flushDestroy(){
        this.isDestroyed = false;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}

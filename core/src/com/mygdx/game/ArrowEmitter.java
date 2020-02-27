package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.sql.Clob;

import javax.xml.bind.annotation.XmlElementDecl;

import jdk.nashorn.internal.objects.Global;
import sun.nio.fs.Globs;

public class ArrowEmitter {
    private static final ArrowEmitter ourInstance = new ArrowEmitter();
    public static ArrowEmitter getInstance(){
        return ourInstance;
    }
    final Array<Arrow> activeArrows = new Array<Arrow>();
    final Pool<Arrow> arrowPool = new Pool<Arrow>(128,512){
        @Override
        protected Arrow newObject() {
            return new Arrow();
        }
    };
    TextureRegion texture;

    public void reset(){
        arrowPool.clear();
        activeArrows.clear();
    }

    private ArrowEmitter(){
    }



}

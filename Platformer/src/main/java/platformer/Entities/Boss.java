/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

import java.util.ArrayList;

/**
 *
 * @author Joonas
 */
public class Boss extends Entity implements Collidable {
    private final int startposX,startposY, endposX;
    private boolean started;
    private int time;
    private float scale;

    public Boss(int spriteSheetId, float width, float height) {
        super(-1,-1, spriteSheetId, width, height, 100);
        scale = 1;
        startposX = 53*32;
        startposY = 32*32;
        setLocation(startposX, startposY);
        
        endposX = 88*32;
        started = false;
    }
    
    public void update(int delta) {
        
        if (started && getMaxX() < endposX) {
            move(delta);
            setX_vel(getX_vel()+((float)10*((float)delta/1000f)));
        }
        //if at the end of charge range
        else if (started) {
            setX_vel(getX_vel()+((float)50*((float)delta/1000f)));
            time+= delta;
            scale = 1f -(time/3000f);
            if (scale  < 0) {
                scale = 0;
            }
        }
        
    }
    
    public void start() {
        if (!started) {
            started = true;
            setX_vel(100);
        }
    }
    
    public void reset() {
        time = 0;
        started = false;
        setX_vel(0);
        setLocation(startposX, startposY);
    }

    public int getStartposY() {
        return startposY;
    }

    public float getScale() {
        return scale;
    }
    
    
}

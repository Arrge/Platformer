/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

import java.util.ArrayList;

/**
 * a spinning boss entity that goes from a to b
 *
 * @author Joonas
 */
public class Boss extends Entity implements Collidable {

    private final int startposX, startposY, endposX;
    private boolean started;
    private int time;
    private float scale;

    /**
     * create boss monster
     *
     * @param spriteSheetId sprite id of the boss
     * @param width width of the boss
     * @param height height of the boss
     */
    public Boss(int spriteSheetId, float width, float height) {
        super(-1, -1, spriteSheetId, width, height, 100);
        scale = 1;
        startposX = 53 * 32;
        startposY = 32 * 32;
        setLocation(startposX, startposY);

        endposX = 88 * 32;
        started = false;
    }

    /**
     * moves the boss forward if the boss has not reached endposX or
     * alternatively start the death animation
     *
     * @param delta milliseconds since last tick
     */
    public void update(int delta) {

        if (started && getMaxX() < endposX) {
            move(delta);
            setX_vel(getX_vel() + ((float) 10 * ((float) delta / 1000f)));
        } //if at the end of charge range start the death animation
        else if (started) {
            setX_vel(getX_vel() + ((float) 50 * ((float) delta / 1000f)));
            time += delta;
            scale = 1f - (time / 3000f);
            if (scale < 0) {
                scale = 0;
            }
        }

    }

    /**
     * Start the advancement of the boss entity
     */
    public void start() {
        if (!started) {
            started = true;
            setX_vel(100);
        }
    }

    /**
     * reset the boss entity to its default state
     */
    public void reset() {
        time = 0;
        started = false;
        setX_vel(0);
        setLocation(startposX, startposY);
        scale = 1;
    }

    public int getStartposY() {
        return startposY;
    }

    public int getStartposX() {
        return startposX;
    }

    public int getEndposX() {
        return endposX;
    }

    public int getTime() {
        return time;
    }

    public float getScale() {
        return scale;
    }

    public boolean isStarted() {
        return started;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

import java.util.Random;

/**
 * Controllable player character
 *
 * @author Joonas
 */
public class Player extends Entity {

    private boolean onPlatform;
    private int health;
    private float immunityTimer;
    //immunity time in seconds
    private int immunityTime;

    /**
     * create a player entity
     *
     * @param x Starting x coordinate of the player
     * @param y Starting y coordinate of the player
     * @param spriteSheetId sprite id of the player
     * @param width width of the player sprite
     * @param height height of the player sprite
     */
    public Player(int x, int y, int spriteSheetId, int width, int height) {
        super(x, y, spriteSheetId, width, height);
        onPlatform = false;
        health = 50;

        immunityTime = 1;
        immunityTimer = immunityTime + 1;
    }

    /**
     * if player is on platform set negative y velocity
     */
    public void jump() {
        if (isOnPlatform() && Math.abs(getY_vel()) < 10f) {
            setY_vel(-180f);
            setOnPlatform(false);
        }
    }

    /**
     * set negative x velocity
     */
    public void goLeft() {
        if (true) {
            setX_vel(-150f);
        }
    }

    /**
     * set positive x velocity
     */
    public void goRight() {
        if (true) {
            setX_vel(150f);
        }
    }

    /**
     * take damage if not immune and don't let health go below zero
     *
     * @param damage
     * @return true if player died
     */
    public boolean takeDamage(int damage) {

        if (immunityTimer > immunityTime) {
            health -= damage;
            immunityTimer = 0;
        }
        if (health <= 0) {
            health = 50;
            setLocation(50, 100);
            return true;
        }
        return false;
    }

    /**
     * check if player is alive
     *
     * @return true if health is over zero
     */
    public boolean isAlive() {
        return health > 0;

    }

    /**
     * simulates gravity and velocity on the player
     *
     * @param delta milliseconds since last tick
     */
    public void applyGravityAndVelocity(int delta) {

        immunityTimer += 1 * ((float) delta / 1000f);

        //apply gravity
        setY_vel(getY_vel() + 275f * ((float) delta / 1000f));
        //terminal velocity
        Float terminalVelocity = 350f;
        if (getY_vel() > terminalVelocity) {
            setY_vel(terminalVelocity);
        }
        //add small slide to movement
        if (getX_vel() > 0) {
            setX_vel(getX_vel() - 800 * (delta / 1000f));
            if (getX_vel() < 0) {
                setX_vel(0);
            }
        } else if (getX_vel() < 0) {
            setX_vel(getX_vel() + 800 * (delta / 1000f));
            if (getX_vel() > 0) {
                setX_vel(0);
            }
        } else {
            setX_vel(0);
        }
    }

///////////////////////////////////////
/////// get & set methods /////////////
///////////////////////////////////////
    /**
     * get the opacity
     *
     * @return
     */
    public int getOpacity() {
        if (immunityTimer < immunityTime) {
            return new Random().nextInt(255);
        } else {
            return 255;
        }
    }

    public int getHealth() {
        return health;
    }

    /**
     * get the x offset from middle of the screen
     *
     * @return x offset from middle of the screen
     */
    public float getX_offset() {
        return getX() - 400;
    }

    /**
     * get the y offset from middle of the screen
     *
     * @return y offset from middle of the screen
     */
    public float getY_offset() {
        return getY() - 300;
    }

    public boolean isOnPlatform() {
        return onPlatform;
    }

    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }

    public void setImmunityTimer(int immunityTimer) {
        this.immunityTimer = immunityTimer;
    }

    public int getImmunityTime() {
        return immunityTime;
    }

}

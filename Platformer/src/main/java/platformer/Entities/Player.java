/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

/**
 *
 * @author Joonas
 */
public class Player extends Entity {

    private float x_vel, y_vel;
    //save old coordinates after movement to help with collision detection stuff
    private float x_old, y_old;
    private boolean onPlatform;

    public Player(int x, int y, int height, int width) {
        super(x, y, height, width);
        y_vel = 0;
        x_vel = 0;
        onPlatform = false;
    }

    public void jump() {
        if (isOnPlatform()) {
            y_vel = -10;
            setOnPlatform(false);
        }
    }

    public void move() {
        x_old = getX();
        y_old = getY();
        getHitbox().setLocation(getX() + x_vel, getY() + y_vel);
    }

    public void goLeft() {
        x_vel = -2f;
    }

    public void goRight() {
        x_vel = 2f;
    }

    public void applyGravity() {
        if (true) {
            y_vel += 0.2f;
        }
        if (isOnPlatform()) {
            if (x_vel > 0f) {
                x_vel -= 0.25f;
            }
            if (x_vel < 0f) {
                x_vel += 0.25f;
            }
        }
    }

    public boolean xIsInsidePlatform(Entity p) {
        return getX() > p.getX() && getX() < p.getX() + p.getWidth();
    }

    public boolean yIsInsidePlatform(Entity p) {
        return getY() > p.getY() && getY() < p.getY() + p.getHeight();
    }
///////////////////////////////////////
/////// get & set methods /////////////
///////////////////////////////////////

    public float getX_old() {
        return x_old;
    }

    public float getY_old() {
        return y_old;
    }

    public boolean isOnPlatform() {
        return onPlatform;
    }

    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }

    public void setY_vel(float y_vel) {
        this.y_vel = y_vel;
    }

    public float getY_vel() {
        return y_vel;
    }

    public void setX_vel(float x_vel) {
        this.x_vel = x_vel;
    }

    public float getX_vel() {
        return x_vel;
    }

}

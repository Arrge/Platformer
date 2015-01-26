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
    //save old coordinates after movement to help collision detection
    private float x_old, y_old;
    private boolean onPlatform;

    public Player(int x, int y, int spriteX, int spriteY, int width, int height) {
        super(x, y, spriteX, spriteY, width, height);
        y_vel = 0;
        x_vel = 0;
        onPlatform = false;
    }

    public void jump() {
        if (isOnPlatform()) {
            y_vel = -400;
            setOnPlatform(false);
        }
    }

    public void move(int delta) {
        x_old = getX();
        y_old = getY();
        getHitbox().setLocation(getX() + x_vel * ((float) delta / 1000f), getY() + y_vel * ((float) delta / 1000f));
    }

    public void goLeft() {
        if (true) {
            x_vel = -250f;
        }
    }

    public void goRight() {
        if (true) {
            x_vel = 250f;
        }
    }

    public void applyGravityAndVelocity(int delta) {
        y_vel += 800f * ((float) delta / 1000f);
        if (x_vel > 0) {
            x_vel -= 800 * (delta / 1000f);
            if (x_vel < 0) {
                x_vel = 0;
            }
        } else if (x_vel < 0) {
            x_vel += 800 * (delta / 1000f);
            if (x_vel > 0) {
                x_vel = 0;
            }
        } else {
            x_vel = 0;
        }
    }

    public boolean isFalling() {
        return y_vel > 0;
    }

    public boolean isFlying() {
        return y_vel < 0;
    }

    public boolean xIsInsidePlatform(Entity p) {
        return getX() + getWidth() >= p.getX() && getX() <= p.getX() + p.getWidth();
    }

    public boolean yIsInsidePlatform(Entity p) {
        return getY() > p.getY() && getY() < p.getY() + p.getHeight();
    }
///////////////////////////////////////
/////// get & set methods /////////////
///////////////////////////////////////

    public float getX_offset() {
        return getX() - 400;
    }

    public float getY_offset() {
        return getY() - 300;
    }

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

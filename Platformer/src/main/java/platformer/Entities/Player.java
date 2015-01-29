/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

import com.sun.javafx.geom.Vec2f;
import java.util.Random;

/**
 *
 * @author Joonas
 */
public class Player extends Entity {

    //save old coordinates after movement to help collision detection
    private boolean onPlatform;
    private int health;
    private float immunityTimer;
    //immunity time in seconds
    private int immunityTime;

    public Player(int x, int y, int spriteSheetId, int width, int height) {
        super(x, y, spriteSheetId, width, height);
        onPlatform = false;
        health = 100;
        
        immunityTime = 3;
        immunityTimer = immunityTime+1;
    }

    public void jump() {
        if (isOnPlatform() && Math.abs(getY_vel()) < 10f) {
            setY_vel(-200f);
            setOnPlatform(false);
        }
    }

    public void goLeft() {
        if (true) {
            setX_vel(-150f);
        }
    }

    public void goRight() {
        if (true) {
            setX_vel(150f);
        }
    }

    public void takeDamage(int damage) {
        
        if (immunityTimer > immunityTime) {
            health -= damage;
            immunityTimer = 0;
        }
        if (health < 0) {
            health = 0;
        }
        
    }

    public boolean isAlive() {
        return health > 0;
    }

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
    
    
    public int getOpacity() {
        if (immunityTimer < immunityTime) {
            return new Random().nextInt(255);
        }else {
            return 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public float getX_offset() {
        return getX() - 400;
    }

    public float getY_offset() {
        return getY() - 300;
    }

    public boolean isOnPlatform() {
        return onPlatform;
    }

    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }

}

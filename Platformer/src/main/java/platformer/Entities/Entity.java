/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

import org.newdawn.slick.geom.Rectangle;
/**
 * basic platform/entity
 * @author Joonas
 */
public class Entity implements Collidable {

    private float x_vel, y_vel;
    private Rectangle hitbox;
    private float x_old, y_old;
    private int spriteSheetId;
    private int collisionDamage;
    /**
     * entity without collision damage
     * @param x
     * @param y
     * @param spriteSheetId
     * @param width
     * @param height 
     */
    public Entity(float x, float y, int spriteSheetId, float width, float height) {
        this.hitbox = new Rectangle(x, y, width, height);
        this.spriteSheetId = spriteSheetId;
        x_old = x;
        y_old = y;
        y_vel = 0;
        x_vel = 0;
        collisionDamage = 0;
    }
    /**
     * entity with collision damage
     * @param x
     * @param y
     * @param spriteSheetId
     * @param width
     * @param height
     * @param collisionDamage 
     */
    public Entity(float x, float y, int spriteSheetId, float width, float height, int collisionDamage) {
        this.hitbox = new Rectangle(x, y, width, height);
        this.spriteSheetId = spriteSheetId;
        x_old = x;
        y_old = y;
        y_vel = 0;
        x_vel = 0;
        this.collisionDamage = collisionDamage;
    }
    /**
     * 
     * @param x
     * @param y 
     */
    public void setLocation(float x, float y) {
        getHitbox().setLocation(x, y);
    }
    /**
     * set location to currentlocation + velocity
     * @param delta milliseconds since last tick
     */
    public void move(int delta) {
        x_old = getX();
        y_old = getY();
        getHitbox().setLocation(getX() + x_vel * ((float) delta / 1000f), getY() + y_vel * ((float) delta / 1000f));
    }

    /////////////////////////////////////////////////////////////////
    public int getSpriteSheetId() {
        return spriteSheetId;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public float getHeight() {
        return Math.round(hitbox.getHeight());
    }

    public float getWidth() {
        return Math.round(hitbox.getWidth());
    }

    public float getX() {
        return hitbox.getX();
    }

    public float getY() {
        return hitbox.getY();
    }

    public float getMaxX() {
        return hitbox.getMaxX();
    }

    public float getMaxY() {
        return hitbox.getMaxY();
    }

    public float getX_old() {
        return x_old;
    }

    public float getY_old() {
        return y_old;
    }

    public float getMaxX_old() {
        return x_old + getWidth();
    }

    public float getMaxY_old() {
        return y_old + getHeight();
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

    @Override
    public int getCollisionDamage() {
        return collisionDamage;
    }
}

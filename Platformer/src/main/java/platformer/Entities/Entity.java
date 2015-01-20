/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Joonas
 */
public class Entity {
    private int x,y,height,width;
    private Rectangle hitbox;

    public Entity(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        hitbox.setLocation(x, y);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    
    
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
        hitbox.setLocation(x, y);
    }
}

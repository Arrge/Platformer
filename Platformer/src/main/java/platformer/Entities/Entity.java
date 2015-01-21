/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class Entity {

    private Rectangle hitbox;
    Color c;

    public Entity(int x, int y, int height, int width) {
        this.hitbox = new Rectangle(x, y, width, height);
        c = Color.white;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setLocation(float x, float y) {
        getHitbox().setLocation(x, y);
    }

    public void setC(Color c) {
        this.c = c;
    }

    public Color getC() {
        return c;
    }

    public float getX() {
        return hitbox.getX();
    }

    public float getY() {
        return hitbox.getY();
    }

    public float getHeight() {
        return hitbox.getHeight();
    }

    public float getWidth() {
        return hitbox.getWidth();
    }
}

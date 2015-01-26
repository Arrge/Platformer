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
    int spriteX, spriteY;
    Color c;

    public Entity(int x, int y, int spriteX, int SpriteY, int width, int height) {
        this.hitbox = new Rectangle(x, y, width, height);
        this.spriteX = spriteX;
        this.spriteY = SpriteY;
        c = Color.white;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setLocation(float x, float y) {
        getHitbox().setLocation(x, y);
    }

    public float getX() {
        return hitbox.getX();
    }

    public float getY() {
        return hitbox.getY();
    }

    public void setC(Color c) {
        this.c = c;
    }

    public Color getC() {
        return c;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public float getHeight() {
        return hitbox.getHeight();
    }

    public float getWidth() {
        return hitbox.getWidth();
    }
}

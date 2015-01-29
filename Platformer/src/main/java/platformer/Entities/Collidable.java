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
public interface Collidable {

    public float getMaxX();

    public float getMaxY();

    public float getX();

    public float getY();

    public float getMaxX_old();

    public float getMaxY_old();

    public float getX_old();

    public float getY_old();

    public float getX_vel();

    public float getY_vel();

    public int getCollisionDamage();

    public Rectangle getHitbox();

    public int getSpriteSheetId();

}

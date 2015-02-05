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
public class PatrollingEnemy extends Entity implements Collidable{
    private int radiusMinX,radiusMaxX;
   
    
    public PatrollingEnemy(int collisionDamage,int radiusMinX,int radiusMaxX, Entity e) {
        super(e.getX(), e.getY(), e.getSpriteSheetId(), e.getWidth(), e.getHeight(),collisionDamage);
        this.radiusMinX = radiusMinX;
        this.radiusMaxX = radiusMaxX;
        setX_vel(150);
        System.out.println(radiusMinX);
        System.out.println(radiusMaxX);
    }
    /**
     * moves the enemy inside the patrol radius
     * @param delta milliseconds since last tick
     */
    public void update(int delta) {
        move(delta);
        if (getX() < radiusMinX) {
            setLocation(radiusMinX, getY());
            setX_vel(150);
        }
        else if (getMaxX() > radiusMaxX) {
            setLocation(radiusMaxX-getWidth(), getY());
            setX_vel(-150); 
        }
    }
}

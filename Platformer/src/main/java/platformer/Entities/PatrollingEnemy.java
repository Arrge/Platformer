/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.entities;

/**
 * Enemy that moves back and forth inside its patrol radius
 *
 * @author Joonas
 */
public class PatrollingEnemy extends Entity implements Collidable {

    private int radiusMinX, radiusMaxX;

    /**
     * create a moving enemy with a patrolling radius
     *
     * @param collisionDamage collision damage
     * @param radiusMinX minimum x coordinate of the patrolling radius
     * @param radiusMaxX maximum x coordinate of the patrolling radius
     * @param e patrolling enemy entity placeholder from SpriteHandlers maptiles
     */
    public PatrollingEnemy(int collisionDamage, int radiusMinX, int radiusMaxX, Entity e) {
        super(e.getX(), e.getY(), e.getSpriteSheetId(), e.getWidth(), e.getHeight(), collisionDamage);
        this.radiusMinX = radiusMinX;
        this.radiusMaxX = radiusMaxX;
        setX_vel(150);
    }

    /**
     * moves the enemy inside the patrol radius
     *
     * @param delta milliseconds since last tick
     */
    public void update(int delta) {
        move(delta);
        if (getX() < radiusMinX) {
            setLocation(radiusMinX, getY());
            setX_vel(150);
        } else if (getMaxX() > radiusMaxX) {
            setLocation(radiusMaxX - getWidth(), getY());
            setX_vel(-150);
        }
    }
}

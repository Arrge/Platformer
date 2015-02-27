/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Entities;

/**
 * a platform that deals damage when collided with
 *
 * @author Joonas
 */
public class Spike extends Entity implements Collidable {

    /**
     * create an entity with collision damage
     *
     * @param collisionDamage damage that the entity does to the player on
     * collision
     * @param x x coordinate of the spike
     * @param y y coordinate of the spike
     * @param spriteSheetId sprite id of the spike
     * @param width width of the spike
     * @param height height of the spike
     */
    public Spike(int collisionDamage, int x, int y, int spriteSheetId, int width, int height) {
        super(x, y, spriteSheetId, width, height, collisionDamage);

    }

    /**
     * convert entity to spike
     *
     * @param collisionDamage
     * @param e
     */
    public Spike(int collisionDamage, Entity e) {

        super(e.getX(), e.getY(), e.getSpriteSheetId(), e.getWidth(), e.getHeight(), collisionDamage);
    }
}

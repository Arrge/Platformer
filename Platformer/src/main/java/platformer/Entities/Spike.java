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
public class Spike extends Entity implements Collidable {

    

    public Spike(int collisionDamage, int x, int y, int spriteSheetId, int width, int height) {
        super(x, y, spriteSheetId, width, height,collisionDamage);
        
    }

    //convert entity e to spike
    public Spike(int collisionDamage, Entity e) {
        
        super(e.getX(), e.getY(), e.getSpriteSheetId(), e.getWidth(), e.getHeight(),collisionDamage);
    }
}

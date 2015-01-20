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
public class Player extends Entity {
    int health;
    
    public Player(int x, int y) {
        super(x, y, 15,4);
        this.health = 100;
    }
    
    //returns true if dead
    public boolean damage(int amount) {
        if (health - amount <= 0) {
            health = 0;
            return true;
        }
        health -= amount;
        return false;
    }
    
}
